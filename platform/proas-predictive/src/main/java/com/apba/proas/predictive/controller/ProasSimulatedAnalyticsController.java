package com.apba.proas.predictive.controller;

import com.apba.proas.backend.controller.analytics.AnalyticsWebClientConfig;
import com.apba.proas.backend.controller.analytics.ProasBackendConfig;
import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.AoiState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

/*
 * Clase que se ejecuta en remoto, lo hará en Java
 */
@RequestMapping("/proas-predictive")
@RestController
public class ProasSimulatedAnalyticsController {
    public static int AOI_SIMULATED_NUMBER = 9;

    @Autowired
    AnalyticsWebClientConfig analyticsWebClientConfig;

    public ProasSimulatedAnalyticsController() {
        super();
    }

    @GetMapping(value = "/test")
    public String getTest() {
        log("/test");
        return "{msg : Hello from /proas-predictives}";
    }

    @GetMapping(value = "/config")
    public ProasBackendConfig getConfig() {
        log("/config");
        return new ProasBackendConfig(analyticsWebClientConfig);
    }

    @RequestMapping(value = "/security/{id}", method = RequestMethod.GET)
    public AoiState getAoiStateById(@PathVariable("id") int id) {
        log("/security/" + id);
        AOI aoi = AoiBuilder.getAoi(id);
        if (aoi != null && aoi.getId() == id) {
            return aoi.getAoiState();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("AOI id=%s not found, exists id=%d ", id, AoiBuilder.getFirstAoi().getId()));
        }
    }

    @PostConstruct
    public void init() {
    }

    Logger logger = LoggerFactory.getLogger(ProasSimulatedAnalyticsController.class);

    void log(String s) {
        logger.info("----Http::/proas-predictive" + s);
    }

}
