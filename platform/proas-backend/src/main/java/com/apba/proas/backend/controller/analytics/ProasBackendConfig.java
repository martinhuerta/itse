package com.apba.proas.backend.controller.analytics;

public class ProasBackendConfig {

    public static String CONFIG_VERSION = "config-hardcoded";

    // String url = "http://localhost:8762"; // predictive
    String url = "http://localhost"; // predictive
    int port = 8761;
    String applicationUri = "/proas-predictive";
    int timeout = 300;
    String uriTest = "/test";
    String uriConfig = "/config";
    String uriVessel = "/vessel";
    String uriSecurity = "/security/{id}";
    String version = CONFIG_VERSION;

    public ProasBackendConfig() {
        super();
    }

    public ProasBackendConfig(ProasBackendConfig p) {
        super();
        if (p != null) {
            this.applicationUri = p.applicationUri;
            this.uriConfig = p.uriConfig;
            this.uriSecurity = p.uriSecurity;
            this.uriVessel = p.uriVessel;
            this.timeout = p.timeout;
            this.url = p.url;
            this.port = p.port;
            this.version = p.version;
        }
    }

    @Override
    public String toString() {
        String s = getClass().getName() + " { "
                + " url = " + url
                + " port = " + port
                + ", applicationUri = " + applicationUri
                + ", timeout = " + timeout
                + ", uriConfig = " + uriConfig
                + ", uriVessel = " + uriVessel
                + ", uriSecurity = " + uriSecurity
                + ", version = " + version
                + " }";
        return s;
    }

    public String getUriTest() {
        return uriTest;
    }

    public void setUriTest(String uriTest) {
        this.uriTest = uriTest;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    public String getUriSecurity() {
        return uriSecurity;
    }

    public void setUriSecurity(String svcSecurity) {
        this.uriSecurity = svcSecurity;
    }

    public String getUriVessel() {
        return uriVessel;
    }

    public void setUriVessel(String svcVessel) {
        this.uriVessel = svcVessel;
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

    public String getUriConfig() {
        return uriConfig;
    }

    public void setUriConfig(String svcConfig) {
        this.uriConfig = svcConfig;
    }

}