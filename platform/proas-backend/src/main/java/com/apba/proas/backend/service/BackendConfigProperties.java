package com.apba.proas.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

import com.apba.proas.backend.controller.analytics.AnalyticsWebClientConfig;

@Configuration
@ConfigurationProperties(prefix = "proas-backend")
@ConfigurationPropertiesScan("com.apba.proas.backend")
public class BackendConfigProperties {
    public static String CONFIG_VERSION = "memory";

    String version = CONFIG_VERSION;

    @Autowired
    AnalyticsWebClientConfig webClientConfig;

    public BackendConfigProperties() {
        super();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
