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
import com.apba.proas.backend.model.JSonStr;
import com.apba.proas.backend.model.Variable;
import com.apba.proas.backend.model.Vessel;
import com.apba.proas.backend.service.ProasBackendAoiService;

@SpringBootTest
class ProasBackendApplicationTests {
	Logger logger = LoggerFactory.getLogger(ProasBackendApplicationTests.class);
	AOI aoi;
	ProasBackendAoiController controller;
	ProasBackendAoiService service;
	ProasBackendForwardAnalyticsController analyticsController;
	AnalyticsWebClient analyticsWebClient;
	AnalyticsWebClientConfig config;

	void init() {
		aoi = AoiBuilder.buildAOI(1, Variable.Type.WIND.toString());
		AoiBuilder.buildAOI(9, Variable.Type.WIND.toString());
		controller = new ProasBackendAoiController();
		controller.init();
		service = controller.getService();
		analyticsController = new ProasBackendForwardAnalyticsController();
		analyticsController.getAnalyticsWebClient();

		analyticsWebClient = new AnalyticsWebClient();
		config = analyticsWebClient.getAnalyticsWebClientConfig();
	}

	// Spring
	@Test
	void contextLoads() {
	}

	// JSonStr
	@Test
	void jSonStrTest() {
		assert aoi != null;
		JSonStr.getJSonStr().obj2json(aoi.getVessel());
	}

	@Test
	void configTest() {
		AnalyticsWebClient a = new AnalyticsWebClient();
		String s = a.getAnalyticsWebClientConfig().getConfig();
		log("Configuracion: " + s);
	}

	@Test
	void localTest() {
		init();
		log("Local AOIs: " + AoiBuilder.getAois());
		aoi = AoiBuilder.buildAOI(1, Variable.Type.WIND.toString());
		AoiBuilder.buildAOI(9, Variable.Type.WIND.toString());
		AOI aoi1 = AoiBuilder.getAoi(9);
		assert aoi1 != null;
		assert aoi1.getId() == 9;
		log("Servicio en local: service.getConfig()=" + service.getConfig());
		log("Servicio con http: " + controller.getConfig());
	}

	@Test
	void getHttpVessel() {
		init();
		analyticsWebClient.getAnalyticsWebClientConfig();
		Vessel x = analyticsWebClient.getResponse(config.getSvcVessel())
				.bodyToMono(Vessel.class)
				.block(Duration.ofSeconds(config.getTimeout()));
		assert x != null;
		log("Http: Vessel: " + x);
	}

	private void log(String s) {
		logger.debug("-----------" + s);
	}

}
