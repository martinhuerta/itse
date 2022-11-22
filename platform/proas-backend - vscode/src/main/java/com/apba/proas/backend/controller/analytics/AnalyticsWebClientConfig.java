package com.apba.proas.backend.controller.analytics;

import org.springframework.stereotype.Component;

// @Configuration
// @ConfigurationProperties(prefix = "proas-predictive")
// @ComponentScan("proas-predictive")
@Component
public class AnalyticsWebClientConfig {

    // @Value("${url}")
    String url = "http://localhost:8762";

    // @Value("${application}")
    String application = "/proas-predictive";

    // @Value("${timeout}")
    int timeout = 30;

    // @Value("${svc.vessel}")
    String test = "/test";

    // @Value("${svc.vessel}")
    String svcVessel = "/vessel";

    // @Value("${svc.security}")
    String svcSecurity = "/security/{id}";

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
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
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}