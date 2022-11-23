package com.apba.proas.backend.predictive.controller;

import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.AoiState;
import com.apba.proas.backend.model.Vessel;

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

    @GetMapping(value = "/config")
    public String getConfig() {
        System.out.print("-------------/test------------");
        return "{ 'msg' : 'Hello from proas-predictive' }";
    }

    @GetMapping(value = "/vessel")
    public Vessel getVessel() {
        return AoiBuilder.getFirstAoi().getVessel();
    }

    @RequestMapping(value = "/security/{id}", method = RequestMethod.GET)
    public AoiState getAoiStateById(@PathVariable("id") int id) {
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

}
