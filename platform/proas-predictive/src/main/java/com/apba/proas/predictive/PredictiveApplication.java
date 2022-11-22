package com.apba.proas.predictive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.OperationKpiType;
import com.apba.proas.backend.service.JSonStr;

@SpringBootApplication
public class PredictiveApplication {

	public static int AOI_SIMULATED_NUMBER = 9;

	public static void main(String[] args) {
		AOI aoi = AoiBuilder.buildAOI(AOI_SIMULATED_NUMBER, OperationKpiType.SECURITY.toString());
		System.out.println("AOI: " + JSonStr.getSonStr().obj2json(aoi));

		SpringApplication.run(PredictiveApplication.class, args);
	}

}
