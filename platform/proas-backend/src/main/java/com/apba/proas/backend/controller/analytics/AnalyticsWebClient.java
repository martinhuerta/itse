package com.apba.proas.backend.controller.analytics;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.apba.proas.backend.model.AoiState;
import com.apba.proas.backend.model.Vessel;

@Service
// @Configurable
public class AnalyticsWebClient {

    // @Autowired
    AnalyticsWebClientConfig analyticsWebClientConfig = new AnalyticsWebClientConfig();

    // AnalyticsWebClientConfig analyticsWebClientConfig = new
    // AnalyticsWebClientConfig();

    private WebClient webClient;

    public String getConfig() {
        log("---------AnalyticsWebClientConfig = " + analyticsWebClientConfig);
        String x = getResponse(analyticsWebClientConfig.getConfig())
                .bodyToMono(String.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
        log("----Vessel:" + x);
        return x;
    }

    public Vessel getVessel() {
        Vessel x = getResponse(analyticsWebClientConfig.getSvcVessel())
                .bodyToMono(Vessel.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
        log("----Vessel:" + x);
        return x;
    }

    public AoiState getSecurityIndicator(@PathVariable int id) {
        AoiState x = getResponse(analyticsWebClientConfig.getSvcSecurity(), id)
                .bodyToMono(AoiState.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
        log("----Vessel:" + x);
        return x;
    }

    public AnalyticsWebClient() {
        getWebClient();
        log("----AnlyticsWebClient inicializado OK");
    }

    public ResponseSpec getResponse(String uri, Object... objects) {
        log("---------- LLamando a uri: " + uri);
        return this.getWebClient()
                .get()
                .uri(uri, objects)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve();
    }

    public WebClient getWebClient() {
        if (webClient == null) {
            webClient = WebClient.builder()
                    .baseUrl(analyticsWebClientConfig.getUrl() + analyticsWebClientConfig.getApplication())
                    .build();
        }
        return webClient;
    }

    private void log(String s) {
        System.out.println(s);
    }

    public AnalyticsWebClientConfig getAnalyticsWebClientConfig() {
        return analyticsWebClientConfig;
    }

    // Mono<String> resp = headersSpec.exchangeToMono(response -> {
    // if (response.statusCode().equals(HttpStatus.OK)) {
    // return response.bodyToMono(String.class);
    // } else if (response.statusCode().is4xxClientError()) {
    // return Mono.just("Error response");
    // } else {
    // return response.createException()
    // .flatMap(Mono::error);
    // }
    // });

}