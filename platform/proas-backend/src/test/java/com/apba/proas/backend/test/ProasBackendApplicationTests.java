package com.apba.proas.backend.test;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import com.apba.proas.backend.controller.analytics.AnalyticsWebClient;
import com.apba.proas.backend.controller.analytics.AnalyticsWebClientConfig;
import com.apba.proas.backend.controller.analytics.ProasBackendConfig;
import com.apba.proas.backend.controller.analytics.ProasBackendForwardAnalyticsController;
import com.apba.proas.backend.controller.server.ProasBackendAoiController;
import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.AoiState;
import com.apba.proas.backend.model.JSonStr;
import com.apba.proas.backend.model.Variable;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProasBackendApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(ProasBackendApplicationTests.class);
	static AOI aoi;
	static int otherID = 9;

	@Autowired
	ProasBackendAoiController backendController;

	@Autowired
	ProasBackendForwardAnalyticsController analyticsController;

	@Autowired
	AnalyticsWebClient analyticsWebClient;

	@Autowired
	AnalyticsWebClientConfig config;

	public ProasBackendApplicationTests() {
		super();
	}

	@PostConstruct
	void init() {
		if (aoi == null) {
			aoi = AoiBuilder.buildAOI(1, Variable.Type.WIND.toString());
			AoiBuilder.buildAOI(otherID, Variable.Type.WIND.toString());
		}
	}

	// Spring
	@Test
	void contextLoads() {
		assert backendController != null;
	}

	// JSonStr
	@Test
	void jSonStrTest() {
		assert aoi != null;
		log("vessel= " + JSonStr.getJSonStr().obj2json(aoi.getVessel()));
	}

	@Test
	void configFromFileTests() {
		log("Local AOIs: " + AoiBuilder.getAois());
		AOI aoi1 = AoiBuilder.getAoi(9);
		assert aoi1 != null;
		assert aoi1.getId() == 9;
		log("Servicio en local: service.getConfig()=" + backendController.getService().getConfig());

		ProasBackendConfig cfgHttp = backendController.getConfig();
		assert !ProasBackendConfig.CONFIG_VERSION.equals(cfgHttp.getVersion());
		log("Servicio con http: " + cfgHttp.toString());
	}

	@Test
	void httpProasBackendTest() {
		String s;
		s = this.restTemplate.getForObject("http://localhost:" + port + "/proas-backend/version", String.class);
		assert s != null;
		log("test: " + s);

		s = this.restTemplate.getForObject("http://localhost:" + port + "/proas-backend/config", String.class);
		assert s != null && !s.contains(ProasBackendConfig.CONFIG_VERSION);
		log("config: " + s);

		s = this.restTemplate.getForObject("http://localhost:" + port + "/proas-backend/vessel", String.class);
		assert s != null && s.contains(aoi.getVessel().getVesselType().toString());
		log("vessel: " + s);

	}

	@Test
	void httpRemoteAnalyticsOnThisPortTest() {
		String s;
		int remotePort = port;

		// Para probar en remoto, otra aplicación Analytics levantada poner este puerto
		// remotePort = config.getPort();

		s = this.restTemplate.getForObject("http://localhost:" + remotePort + "/proas-backend/analytics/version",
				String.class);
		assert s != null && !s.contains("error");
		log("test: " + s);

		s = this.restTemplate.getForObject("http://localhost:" + remotePort + "/proas-backend/analytics/config",
				String.class);
		assert s != null && !s.contains("error");
		log("config: " + s);

		s = this.restTemplate.getForObject("http://localhost:" + remotePort + "/proas-backend/analytics/vessel",
				String.class);
		assert s != null && s.contains("Hello");
		log("vessel: " + s);

		s = this.restTemplate.getForObject("http://localhost:" + remotePort + "/proas-backend/analytics/security/{id}",
				String.class);
		assert s != null && s.contains("Hello");
		log("vessel: " + s);
	}

	@Test
	void remoteWebClientOnRemotePortTest() {
		analyticsWebClient.getAnalyticsWebClientConfig();
		try {

			ProasBackendConfig c = analyticsController.getConfig();
			assert c != null && !ProasBackendConfig.CONFIG_VERSION.equals(c.getVersion());

			String s = analyticsController.getTest();
			assert s != null;

			AoiState a = analyticsController.getSecurityIndicatorById(otherID);
			assert a != null && a.getAoiId() == otherID;

		} catch (Exception e) {
			log("ERROR - Debes tener el servicio ProasBackendApplication lanzado");
			e.printStackTrace();
			throw e;
		}
	}

	private void log(String s) {
		System.out.println("############# " + s + " ############# ");
	}

}
