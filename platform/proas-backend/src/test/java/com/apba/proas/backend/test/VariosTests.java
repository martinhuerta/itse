package com.apba.proas.backend.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.apba.proas.backend.controller.analytics.AnalyticsWebClientConfig;
import com.apba.proas.backend.service.BackendConfigProperties;

@SpringBootTest
public class VariosTests {

    @Autowired
    BackendConfigProperties backendConfigProperties;

    @Autowired
    AnalyticsWebClientConfig analyticsWebClientConfig;

    @Test
    void configVersionTet() {
        assert backendConfigProperties != null;
        assert !BackendConfigProperties.CONFIG_VERSION.equals(backendConfigProperties.getVersion());
    }

    @Test
    void configWebClientTest() {
        assert analyticsWebClientConfig != null;
        assert !AnalyticsWebClientConfig.CONFIG_VERSION.equals(analyticsWebClientConfig.getVersion());
    }

}
