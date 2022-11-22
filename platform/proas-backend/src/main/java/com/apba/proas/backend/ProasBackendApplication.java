package com.apba.proas.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.JSonStr;
import com.apba.proas.backend.model.Operation;

@SpringBootApplication
public class ProasBackendApplication {

	public static int AOI_SIMULATED_NUMBER = 9;
	static AOI aoi;

	public static void main(String[] args) {

		SpringApplication.run(ProasBackendApplication.class, args);
		Logger log = LoggerFactory.getLogger(ProasBackendApplication.class);
		log.info("---------------info---ProasBackendApplication iniciado");
		aoi = AoiBuilder.buildAoiWind();
		AoiBuilder.buildAOI(AOI_SIMULATED_NUMBER, Operation.Type.SECURITY.toString());
		JSonStr.getJSonStr().obj2json(aoi.getVessel());

	}
}