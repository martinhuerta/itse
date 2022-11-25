package com.apba.proas.backend.controller.analytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apba.proas.backend.model.AoiState;

@Component
@RequestMapping("/proas-backend/analytics")
@RestController
public class ProasBackendForwardAnalyticsController {

    @Autowired
    private AnalyticsWebClient analyticsWebClient;

    public ProasBackendForwardAnalyticsController() {
        super();
    }

    @GetMapping(value = "/test")
    public String getTest() {
        return analyticsWebClient.getTest();
    }

    @GetMapping(value = "/version")
    public String getVersion() {
        return analyticsWebClient.getAnalyticsWebClientConfig().getVersion();
    }

    @GetMapping(value = "/config")
    public ProasBackendConfig getConfig() {
        return new ProasBackendConfig(analyticsWebClient.getConfig());
    }

    @GetMapping(value = "/security/{id}")
    public AoiState getSecurityIndicatorById(@PathVariable("id") int id) {
        AoiState aoiState = null;
        try {
            log("/security/" + id);
            aoiState = analyticsWebClient.getSecurityIndicator(id);
        } catch (Exception e) {
            log(this.getClass().getName() + "---------/proas-backend/security/{}id} -- ERROR " + e);
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

    Logger logger = LoggerFactory.getLogger(ProasBackendForwardAnalyticsController.class);

    void log(String s) {
        logger.info("--------Http::/proas-backend/analytics" + s);
    }

}