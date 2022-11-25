package com.apba.proas.backend;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import com.apba.proas.backend.controller.analytics.ProasBackendConfig;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.Operation;

@SpringBootApplication
@ConfigurationPropertiesScan("com.apba.proas.backend")
public class ProasBackendApplication {

	public static int AOI_SIMULATED_NUMBER = 9;

	@Autowired
	ProasBackendConfig backendConfig;

	@PostConstruct
	public void init() {
		Logger log = LoggerFactory.getLogger(ProasBackendApplication.class);
		log.info("---------------info---ProasBackendApplication iniciado");
		AoiBuilder.buildAoiWind();
		AoiBuilder.buildAOI(AOI_SIMULATED_NUMBER, Operation.Type.SECURITY.toString());

		String version = backendConfig != null ? backendConfig.getVersion()
				: ProasBackendConfig.CONFIG_VERSION;
		LoggerFactory.getLogger(ProasBackendApplication.class)
				.info("---------------- ProasBackendApplication - VERSION CONFIGURACION = " + version);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProasBackendApplication.class, args);
	}

}