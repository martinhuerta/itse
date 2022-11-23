package com.apba.proas.backend.test;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import com.apba.proas.backend.controller.analytics.AnalyticsWebClient;
import com.apba.proas.backend.controller.analytics.AnalyticsWebClientConfig;
import com.apba.proas.backend.controller.analytics.ProasBackendForwardAnalyticsController;
import com.apba.proas.backend.controller.server.ProasBackendAoiController;
import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.AoiState;
import com.apba.proas.backend.model.JSonStr;
import com.apba.proas.backend.model.Variable;
import com.apba.proas.backend.model.Vessel;
import com.apba.proas.backend.service.ProasBackendAoiService;

@SpringBootTest
class ProasBackendApplicationTests {
	Logger logger = LoggerFactory.getLogger(ProasBackendApplicationTests.class);
	AOI aoi;
	static int otherID = 9;
	ProasBackendAoiController controller;
	ProasBackendAoiService service;
	ProasBackendForwardAnalyticsController analyticsController;

	AnalyticsWebClient analyticsWebClient;
	AnalyticsWebClientConfig config;

	public ProasBackendApplicationTests() {
		super();
	}

	void init() {
		if (aoi == null) {
			aoi = AoiBuilder.buildAOI(1, Variable.Type.WIND.toString());
			AoiBuilder.buildAOI(otherID, Variable.Type.WIND.toString());
			controller = new ProasBackendAoiController();
			controller.init();
			service = controller.getService();
			analyticsController = new ProasBackendForwardAnalyticsController();
			analyticsWebClient = analyticsController.getAnalyticsWebClient();
			config = analyticsWebClient.getAnalyticsWebClientConfig();
		}
	}

	// Spring
	@Test
	void contextLoads() {
	}

	// JSonStr
	@Test
	void jSonStrTest() {
		init();
		assert aoi != null;
		JSonStr.getJSonStr().obj2json(aoi.getVessel());
	}

	@Test
	void configTest() {
		init();
		String s = config.getVersion();
		// int i = s.indexOf("hardcoded");
		// assert i < 0;
		log("getConfig(): " + s);
	}

	@Test
	void localTest() {
		init();
		log("Local AOIs: " + AoiBuilder.getAois());
		aoi = AoiBuilder.buildAOI(1, Variable.Type.WIND.toString());
		AoiBuilder.buildAOI(otherID, Variable.Type.WIND.toString());
		AOI aoi1 = AoiBuilder.getAoi(9);
		assert aoi1 != null;
		assert aoi1.getId() == 9;
		log("Servicio en local: service.getConfig()=" + service.getConfig());
		log("Servicio con http: " + controller.getConfig());
	}

	@Test
	void getHttpConfig() {
		init();
		try {
			String x = analyticsWebClient.getResponse(config.getConfig())
					.bodyToMono(String.class)
					.block(Duration.ofSeconds(config.getTimeout()));
			assert x != null;
			log("Test getHttpConfig() OK: " + x);
		} catch (Exception e) {
			log("ERROR - Debes tener el servicio ProasBackendApplication lanzado");
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	void getHttpVessel() {
		init();
		Vessel v = aoi.getVessel();
		analyticsWebClient.getAnalyticsWebClientConfig();
		try {
			Vessel x = analyticsWebClient.getResponse(config.getSvcSecurity())
					.bodyToMono(Vessel.class)
					.block(Duration.ofSeconds(config.getTimeout()));
			assert x != null;
			assert x.getDWT() == v.getDWT();
			log("Test getHttpVessel() OK: " + x);
		} catch (Exception e) {
			log("ERROR - Debes tener el servicio ProasBackendApplication lanzado");
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	void getHttpSecurity() {
		init();
		try {
			AoiState x = analyticsWebClient.getResponse(config.getSvcVessel(), otherID)
					.bodyToMono(AoiState.class)
					.block(Duration.ofSeconds(config.getTimeout()));
			assert x != null;
			assert x.getAoiId() == otherID;
			log("Test getHttpSecurity() OK: " + x);
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
