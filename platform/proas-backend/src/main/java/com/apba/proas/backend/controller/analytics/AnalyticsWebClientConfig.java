package com.apba.proas.backend.controller.analytics;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "proas-predictive")
// // @ComponentScan("proas-predictive")
// @Component
public class AnalyticsWebClientConfig {

    public static String CONFIG_VERSION = "memory";

    // String url = "http://localhost:8762"; // predictive
    String url = "http://localhost:8761"; // predictive
    String applicationUri = "/proas-predictive";
    int timeout = 300;
    String config = "/config";
    String svcVessel = "/vessel";
    String svcSecurity = "/security/{id}";
    String version = CONFIG_VERSION;

    @Override
    public String toString() {
        String s = getClass().getName() + " { "
                + " url = " + url
                + ", applicationUri = " + applicationUri
                + ", timeout = " + timeout
                + ", svcVessel = " + svcVessel
                + ", svcSecurity = " + svcSecurity
                + ", version = " + version
                + " }";
        return s;
    }

    public AnalyticsWebClientConfig() {
        super();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSvcSecurity() {
        return svcSecurity;
    }

    public void setSvcSecurity(String svcSecurity) {
        this.svcSecurity = svcSecurity;
    }

    public String getSvcVessel() {
        return svcVessel;
    }

    public void setSvcVessel(String svcVessel) {
        this.svcVessel = svcVessel;
    }

    public String getApplication() {
        return applicationUri;
    }

    public void setApplication(String application) {
        this.applicationUri = application;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

}