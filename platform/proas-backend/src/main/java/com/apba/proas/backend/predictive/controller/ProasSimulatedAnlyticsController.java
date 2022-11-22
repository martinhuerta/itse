package com.apba.proas.backend.predictive.controller;

import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.JSonStr;
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
public class ProasSimulatedAnlyticsController {
    public static int AOI_SIMULATED_NUMBER = 9;

    @GetMapping(value = "/test")
    public String getTest() {
        System.out.print("-------------/test------------");
        return "{ 'msg' : 'Hello from proas-predictive' }";
    }

    @GetMapping(value = "/vessel")
    public String getVesselStr() {
        Vessel v = AoiBuilder.getFirstAoi().getVessel();
        String s = JSonStr.getJSonStr().obj2json(v);
        System.out.print("-------------/vessel = " + s + "------------");
        return s;
    }

    @RequestMapping(value = "/security/{id}", method = RequestMethod.GET)
    public String getAoiStateById(@PathVariable("id") int id) {
        System.out.println("---------------getAoiStateById: punto 1");
        AOI aoi = AoiBuilder.getAoi(id);
        System.out.println("---------------getAoiStateById: punto 2");
        if (aoi != null && aoi.getId() == id) {
            System.out.println("---------------getAoiStateById: punto 3");
            String s = JSonStr.getJSonStr().obj2json(aoi.getAoiState(), true);
            return s;
        } else {
            System.out.println("---------------getAoiStateById: punto 4");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("AOI id=%s not found, exists id=%d ", id, AoiBuilder.getFirstAoi().getId()));
        }
    }

    @PostConstruct
    public void init() {
    }

}
