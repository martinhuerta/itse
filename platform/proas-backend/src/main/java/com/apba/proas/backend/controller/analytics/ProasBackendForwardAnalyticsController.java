package com.apba.proas.backend.controller.analytics;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apba.proas.backend.model.AoiState;
import com.apba.proas.backend.model.Vessel;

@Component
@RequestMapping("/proas-backend/analytics")
@RestController
public class ProasBackendForwardAnalyticsController {

    private AnalyticsWebClient analyticsWebClient = new AnalyticsWebClient();

    @GetMapping(value = "/test")
    public String getTest() {
        return "{msg: Hello from proas-backend}";
    }

    @GetMapping(value = "/config")
    public AnalyticsWebClientConfig getConfig() {
        return analyticsWebClient.getAnalyticsWebClientConfig();
    }

    @GetMapping(value = "/vessel")
    public Vessel getVessel() {
        return analyticsWebClient.getVessel();
    }

    @PostMapping(value = "/vessel")
    @ResponseBody // Serializa el Vessel en el Body del Response, similar al JsonView
    public Vessel getVessel(@RequestBody Vessel vessel) { // RequestBody espera parametros de entrada en el Request.body
        return analyticsWebClient.getVessel();
    }

    @GetMapping(value = "/security/{id}")
    public AoiState getSecurityIndicatorById(@PathVariable("id") int id) {
        AoiState aoiState = null;
        try {
            Logger.getLogger(this.getClass().getName()).info("---------llamada a /proas-backend/security/{}id");

            aoiState = analyticsWebClient.getSecurityIndicator(id);

            Logger.getLogger(this.getClass().getName())
                    .info("---------obtenido AoiState=" + (aoiState == null ? "null" : aoiState.toString()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName())
                    .info("---------/proas-backend/security/{}id} -- ERROR " + e);
            e.printStackTrace();
        }
        return aoiState;
    }

    public AnalyticsWebClient getAnalyticsWebClient() {
        return analyticsWebClient;
    }

    public void setAnalyticsWebClient(AnalyticsWebClient analyticsWebClient) {
        this.analyticsWebClient = analyticsWebClient;
    }

}