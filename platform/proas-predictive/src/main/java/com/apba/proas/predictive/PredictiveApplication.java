package com.apba.proas.predictive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.OperationKpiType;
import com.apba.proas.backend.service.JSonStr;
import com.apba.proas.predictive.controller.ProasSimulatedAnlyticsController;

@SpringBootApplication
public class PredictiveApplication {

	public static void main(String[] args) {
		AOI aoi = AoiBuilder.buildAOI(ProasSimulatedAnlyticsController.AOI_SIMULATED_NUMBER,
				OperationKpiType.SECURITY.toString());
		System.out.println("AOI: " + JSonStr.getSonStr().obj2json(aoi));

		SpringApplication.run(PredictiveApplication.class, args);
	}

}
