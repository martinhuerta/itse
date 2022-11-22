package com.apba.proas.predictive.controller;

import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.Vessel;
import com.apba.proas.backend.service.JSonStr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

/*
 * Clase cliente que desde el Proas-backend llama a los algoritmos remotos.
 * En pruebas, los algoritmos se implementan en Proas
 */
@RequestMapping("/proas-predictive")
@RestController
public class ProasSimulatedAnlyticsController {

    @GetMapping(value = "/test")
    public String getTest() {
        System.out.print("-------------/test------------");
        return "{ 'msg' : 'Hello from proas-predictive' }";
    }

    @GetMapping(value = "/vessel")
    public String getVesselStr() {
        Vessel v = AoiBuilder.getFirstAoi().getVessel();
        String s = JSonStr.getSonStr().obj2json(v);
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
            return JSonStr.getSonStr().obj2json(aoi.getAoiState());
        } else {
            System.out.println("---------------getAoiStateById: punto 4");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("AOI id=%s not found, exists id=%d ", id, AoiBuilder.getFirstAoi().getId()));
        }
    }

    @PostConstruct
    public void init() {
        ;
    }

}
