package com.apba.proas.backend.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.Vessel;

public class ProasBackendAoiService {
    Logger log;

    @PostConstruct
    public void init() {
        log = LoggerFactory.getLogger(ProasBackendAoiService.class);
    }

    public String getTest() {
        return "{ \"msg\" : \"Hello from proas-backend\" }";
    }

    public Vessel getVessel() {
        return AoiBuilder.getFirstAoi().getVessel();
    }

    public Vessel getVessel(String type) {
        return AoiBuilder.getAoiMap().values().stream().filter(
                x -> x.getVessel().getVesselType().toString().equals(type)).findAny()
                .get().getVessel();
    }

    public List<String> getAois() {
        List<String> l = AoiBuilder.getAois();
        return l;
    }

    public AOI getAoiById(int id) {
        AOI aoi = AoiBuilder.getAoi(id);
        if (aoi != null && aoi.getId() == id) {
            return aoi;
        } else {
            return null;
        }
    }
}
