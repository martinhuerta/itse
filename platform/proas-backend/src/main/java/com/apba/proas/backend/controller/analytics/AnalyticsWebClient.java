package com.apba.proas.backend.controller.analytics;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.apba.proas.backend.model.AoiState;
import com.apba.proas.backend.model.Vessel;

@Service
@Configurable
public class AnalyticsWebClient {

    AnalyticsWebClientConfig analyticsWebClientConfig = new AnalyticsWebClientConfig();

    private final WebClient webClient;

    public String getTest() {
        return getResponse(analyticsWebClientConfig.getTest())
                .bodyToMono(String.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
    }

    public Vessel getVessel() {
        return getResponse(analyticsWebClientConfig.getSvcVessel())
                // .toEntity(Vessel.class)
                .bodyToMono(Vessel.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
    }

    public AoiState getSecurityIndicator(@PathVariable int id) {
        return getResponse(analyticsWebClientConfig.getSvcSecurity(), id)
                .bodyToMono(AoiState.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
    }

    public AnalyticsWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(analyticsWebClientConfig.getUrl() + analyticsWebClientConfig.getApplication())
                .build();
    }

    public ResponseSpec getResponse(String uri, Object... objects) {
        return this.webClient.get()
                .uri(uri, objects)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve();
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