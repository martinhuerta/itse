package com.apba.proas.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.AoiBuilder;
import com.apba.proas.backend.model.Vessel;

@Service
public class ProasBackendAoiService {

    public ProasBackendAoiService() {
        super();
    }

    public String getConfig() {
        return "{ \"msg\" : \"Hello from proas-backend: config-code\" }";
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
