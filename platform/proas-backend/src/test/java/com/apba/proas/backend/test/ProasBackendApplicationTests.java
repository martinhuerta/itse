package com.apba.proas.backend.test;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

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

@SpringBootTest
class ProasBackendApplicationTests {
	Logger logger = LoggerFactory.getLogger(ProasBackendApplicationTests.class);
	AOI aoi;
	static int otherID = 9;

	@Autowired
	ProasBackendAoiController backendController;

	@Autowired
	ProasBackendForwardAnalyticsController analyticsController;

	@Autowired
	AnalyticsWebClient analyticsWebClient;

	@Autowired
	AnalyticsWebClientConfig config;

	@Bean
	ProasBackendAoiController gBackendAoiController() {
		return new ProasBackendAoiController();
	}

	@Bean
	ProasBackendForwardAnalyticsController gAnalyticsController() {
		return new ProasBackendForwardAnalyticsController();
	}

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
		init();
		assert aoi != null;
		JSonStr.getJSonStr().obj2json(aoi.getVessel());
	}

	@Test
	void localTest() {
		log("Local AOIs: " + AoiBuilder.getAois());
		AOI aoi1 = AoiBuilder.getAoi(9);
		assert aoi1 != null;
		assert aoi1.getId() == 9;
		log("Servicio en local: service.getConfig()=" + backendController.getService().getConfig());
		AnalyticsWebClientConfig cfgHttp = backendController.getConfig();
		log("Servicio con http: " + cfgHttp.toString());
	}

	@Test
	void getHttpConfig() {
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
		Vessel v = aoi.getVessel();
		analyticsWebClient.getAnalyticsWebClientConfig();
		try {
			Vessel x = analyticsWebClient.getResponse(config.getSvcVessel())
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
		try {
			AoiState x = analyticsWebClient.getResponse(config.getSvcSecurity(), otherID)
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
