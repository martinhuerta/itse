package com.apba.proas.backend.controller.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apba.proas.backend.model.AoiState;
import com.apba.proas.backend.model.Vessel;

@RequestMapping("/proas-backend/analytics")
@RestController
public class ProasBackendForwardAnalyticsController {
    @Autowired
    private AnalyticsWebClient proasPredictiveClient;

    @GetMapping(value = "/test")
    public String getTest() {
        String test = proasPredictiveClient.getTest();
        return "Testo " + test;
    }

    @GetMapping(value = "/vessel")
    public String getVessel() {
        Vessel vessel = proasPredictiveClient.getVessel();
        return "Vessel " + vessel;
    }

    @GetMapping(value = "/aoi/{id}/security")
    public String getSecurityIndicatorById(@PathVariable("id") int id) {
        AoiState aoiState = proasPredictiveClient.getSecurityIndicator(id);
        return "AoiState " + aoiState;
    }
}