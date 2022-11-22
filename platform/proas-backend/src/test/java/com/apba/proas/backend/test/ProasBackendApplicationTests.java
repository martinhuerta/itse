package com.apba.proas.backend.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.VariableType;
import com.apba.proas.backend.service.JSonStr;

@SpringBootTest
class ProasBackendApplicationTests {

	@Test
	void contextLoads() {

		// System.out.println("Salida JSON");
		// System.out.println(jsonstr);

		// JSONObject json = new JSONObject(jsonString); // Convert text to object
		// System.out.println(json.toString(4)); // Print it with specified indentation
	}

	@Test
	void test2() {
		AOI aoi = AoiBuilder.buildAOI(9, VariableType.WIND.toString());
		JSonStr.getSonStr().obj2json(aoi.getVessel());
	}

}
