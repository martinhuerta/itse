package com.apba.proas.backend.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.apba.proas.backend.controller.analytics.AnalyticsWebClientConfig;
import com.apba.proas.backend.controller.analytics.ProasBackendConfig;

@SpringBootTest
public class VariosTests {

    @Autowired
    ProasBackendConfig proasBackendConfig;

    @Autowired
    AnalyticsWebClientConfig analyticsWebClientConfig;

    @Test
    void configVersionTet() {
        assert proasBackendConfig != null;
        assert !ProasBackendConfig.CONFIG_VERSION.equals(proasBackendConfig.getVersion());
    }

    @Test
    void configWebClientTest() {
        assert analyticsWebClientConfig != null;
        assert !AnalyticsWebClientConfig.CONFIG_VERSION.equals(analyticsWebClientConfig.getVersion());
    }

}
