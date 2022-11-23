package com.apba.proas.backend.controller.analytics;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.apba.proas.backend.model.JSonStr;

@Configuration
@ConfigurationProperties(prefix = "proas-predictive")
// @ComponentScan("proas-predictive")
@Component
public class AnalyticsWebClientConfig {

    @Value("${proas-predictive.url}")
    // String url = "http://localhost:8762"; // predictive
    String url = "http://localhost:8761"; // predictive

    @Value("${proas-predictive.application}")
    String applicationUri = "/proas-predictive";

    @Value("${proas-predictive.timeout}")
    int timeout = 300;

    @Value("${proas-predictive.config}")
    String config = "/config";

    @Value("${proas-predictive.svc-vessel}")
    String svcVessel = "/vessel";

    @Value("${proas-predictive.svc-security}")
    String svcSecurity = "/security/{id}";

    @Value("${proas-predictive.version}")
    String version = "version-hardcoded";

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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getClass().getName() + JSonStr.getJSonStr().obj2json(this, true));
        return sb.toString();
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

}