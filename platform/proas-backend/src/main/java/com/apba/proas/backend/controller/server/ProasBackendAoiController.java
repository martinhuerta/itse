package com.apba.proas.backend.controller.server;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apba.proas.backend.controller.analytics.AnalyticsWebClientConfig;
import com.apba.proas.backend.controller.analytics.ProasBackendConfig;
import com.apba.proas.backend.model.AOI;
import com.apba.proas.backend.model.Vessel;
import com.apba.proas.backend.service.ProasBackendAoiService;

/*
 * Clase cliente que desde el Proas-backend llama a los algoritmos remotos.
 * En pruebas, los algoritmos se implementan en Proas
 * 
 * Usar @JsonView(Vessel.class) --> Cuando quieres gobernar qué parámetros salen y
 * cuales no, marcas en el Bean los que estan ligados a est clase
 */
@RequestMapping("/proas-backend")
@RestController
public class ProasBackendAoiController {

    @Autowired
    private ProasBackendAoiService service;

    @Autowired
    private AnalyticsWebClientConfig analyticsWebClientConfig;

    @PostConstruct
    public void init() {
    }

    public ProasBackendAoiService getService() {
        return service;
    }

    public ProasBackendAoiController() {
        super();
    }

    @GetMapping(value = "/test")
    public String getTest() {
        log("/tes");
        return "{msg: Hello from proas-backend}";
    }

    @GetMapping(value = "/version")
    public String getVersion() {
        log("/version");
        return analyticsWebClientConfig.getVersion();
    }

    @GetMapping(value = "/config")
    public ProasBackendConfig getConfig() {
        log("/config");
        return new ProasBackendConfig(analyticsWebClientConfig);
    }

    @GetMapping(value = "/vessel")
    public Vessel getVesselStr() {
        log("/vessel");
        return service.getVessel();
    }

    @GetMapping(value = "/vessel-by-type/{vessel-type}")
    public Vessel getVessel(@RequestParam("vessel-type") String type) {
        log("/vessel-by-type/" + type);
        return service.getVessel(type);
    }

    @RequestMapping(value = "/aois")
    public List<String> getAois() {
        log("/aois");
        return service.getAois();
    }

    @RequestMapping(value = "/aoi/{id}", method = RequestMethod.GET)
    public AOI getAoiById(@PathVariable("id") int id) {
        log("/aoi/" + id);
        AOI aoi = service.getAoiById(id);

        if (aoi != null && aoi.getId() == id) {
            return aoi;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("%s not found, only exists %s ", id, aoi == null ? 0 : aoi.getId()));
        }
    }

    Logger logger = LoggerFactory.getLogger(ProasBackendAoiController.class);

    void log(String s) {
        logger.info("----Http::/proas-backend" + s);
    }

}
