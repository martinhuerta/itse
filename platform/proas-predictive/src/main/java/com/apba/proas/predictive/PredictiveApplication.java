package com.apba.proas.predictive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apba.proas.backend.controller.analytics.AnalyticsWebClientConfig;
import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.JSonStr;
import com.apba.proas.backend.model.Operation;

@SpringBootApplication
public class PredictiveApplication {
	public static int AOI_SIMULATED_NUMBER = 9;

	public static void main(String[] args) {
		AOI aoi = AoiBuilder.buildAOI(AOI_SIMULATED_NUMBER,
				Operation.Type.SECURITY.toString());
		System.out.println("AOI: " + JSonStr.getJSonStr().obj2json(aoi));

		SpringApplication.run(PredictiveApplication.class, args);
	}

	@Bean
	AnalyticsWebClientConfig gAnalyticsWebClientConfig() {
		return new AnalyticsWebClientConfig();
	}

}
