package com.apba.proas.backend.controller.server;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.service.JSonStr;
import com.apba.proas.backend.service.ProasBackendAoiService;

/*
 * Clase cliente que desde el Proas-backend llama a los algoritmos remotos.
 * En pruebas, los algoritmos se implementan en Proas
 */
@RequestMapping("/proas-backend")
@RestController
public class ProasBackendAoiController {
    ProasBackendAoiService service;

    @PostConstruct
    public void init() {
        service = new ProasBackendAoiService();
    }

    @GetMapping(value = "/test")
    public String getTest() {
        return JSonStr.getSonStr().obj2json(service.getTest());
    }

    @NewSpan("obtener /vessel")
    @GetMapping(value = "/vessel")
    public String getVesselStr() {
        return JSonStr.getSonStr().obj2json(service.getVessel());
    }

    @GetMapping(value = "/vessel-by-type/{vessel-type}")
    public String getVessel(@RequestParam("vessel-type") String type) {
        return JSonStr.getSonStr().obj2json(service.getVessel(type));
    }

    @RequestMapping(value = "/aois")
    public List<String> getAois() {
        return service.getAois();
    }

    @RequestMapping(value = "/aoi/{id}", method = RequestMethod.GET)
    public String getAoiById(@PathVariable("id") int id) {
        AOI aoi = service.getAoiById(id);

        if (aoi != null && aoi.getId() == id) {
            return JSonStr.getSonStr().obj2json(aoi);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("%s not found, only exists %s ", id, aoi == null ? 0 : aoi.getId()));
        }
    }

}
