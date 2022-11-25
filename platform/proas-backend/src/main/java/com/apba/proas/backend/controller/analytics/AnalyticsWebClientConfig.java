package com.apba.proas.backend.controller.analytics;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "proas-predictive")
public class AnalyticsWebClientConfig extends ProasBackendConfig {

    public AnalyticsWebClientConfig() {
        super();
    }
}