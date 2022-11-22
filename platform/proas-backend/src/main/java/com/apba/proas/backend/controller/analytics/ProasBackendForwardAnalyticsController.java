package com.apba.proas.backend.controller.analytics;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apba.proas.backend.model.AoiState;
import com.apba.proas.backend.model.Vessel;

@Component
@RequestMapping("/proas-backend/analytics")
@RestController
public class ProasBackendForwardAnalyticsController {

    // @Autowired
    private AnalyticsWebClient analyticsWebClient = new AnalyticsWebClient();

    @GetMapping(value = "/config")
    public String getTest() {
        String config = analyticsWebClient.getConfig();
        return "proas-backend:Config " + config;
    }

    @GetMapping(value = "/vessel")
    public String getVessel() {
        Vessel vessel = analyticsWebClient.getVessel();
        return "Vessel " + vessel;
    }

    @GetMapping(value = "/security/{id}")
    public String getSecurityIndicatorById(@PathVariable("id") int id) {
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
        return "AoiState " + aoiState;
    }

    public AnalyticsWebClient getAnalyticsWebClient() {
        return analyticsWebClient;
    }

    public void setAnalyticsWebClient(AnalyticsWebClient analyticsWebClient) {
        this.analyticsWebClient = analyticsWebClient;
    }

}