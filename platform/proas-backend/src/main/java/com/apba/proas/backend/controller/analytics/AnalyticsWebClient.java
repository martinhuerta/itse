package com.apba.proas.backend.controller.analytics;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import com.apba.proas.backend.model.AoiState;
import com.apba.proas.backend.model.Vessel;

import reactor.core.publisher.Mono;

@Service
// @Configurable
public class AnalyticsWebClient {

    AnalyticsWebClientConfig analyticsWebClientConfig;
    Logger logger;

    public AnalyticsWebClient() {
        analyticsWebClientConfig = new AnalyticsWebClientConfig();
        logger = LoggerFactory.getLogger(AnalyticsWebClient.class);
        log("AnalyticsWebClient inicializado OK");
    }

    public String getConfig() {
        log("AnalyticsWebClientConfig = " + analyticsWebClientConfig);
        String x = getResponse(analyticsWebClientConfig.getConfig())
                .bodyToMono(String.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
        log("Config:" + x);
        return x;
    }

    public Vessel getVessel() {
        Vessel x = getResponse(analyticsWebClientConfig.getSvcVessel())
                .bodyToMono(Vessel.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
        log("Vessel:" + x);
        return x;
    }

    public Vessel getVesselSimple() {
        Vessel x = WebClient
                .create(analyticsWebClientConfig.getUrl() + analyticsWebClientConfig.getApplication())
                .get()
                .uri(analyticsWebClientConfig.getSvcVessel())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Vessel.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));

        log("Vessel:" + x);
        return x;
    }

    public AoiState getSecurityIndicator(@PathVariable int id) {
        AoiState x = getResponse(analyticsWebClientConfig.getSvcSecurity(), id)
                .bodyToMono(AoiState.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
        log("Vessel:" + x);
        return x;
    }

    public ResponseSpec getResponse(String uri, Object... objects) {
        WebClient webClient = WebClient
                .builder()
                .baseUrl(analyticsWebClientConfig.getUrl() + analyticsWebClientConfig.getApplication())
                .build();

        RequestBodyUriSpec bodySpec = webClient.method(HttpMethod.GET);
        bodySpec.accept(MediaType.APPLICATION_JSON);
        bodySpec.uri(uri, objects);
        bodySpec.uri(uriBuilder -> uriBuilder
                .path(uri) // Se especifica el path con posibles prametros
                .queryParam("par1", "miname")
                .queryParam("par2", "misurname")
                .build(objects)); // Se le pasan los parámetros del path
        bodySpec.ifNoneMatch("*");
        // RestTemplate rt=new RestTemplate();
        // rt.

        RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue("data");
        headersSpec.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML);

        // Mono<String> response = headersSpec.exchangeToMono(
        // resp -> responseHandler(resp));
        ResponseSpec rs = bodySpec.retrieve();
        return rs;
    }

    // Gestionar la respuesta
    public Mono<String> responseHandler(ClientResponse response) {
        if (response.statusCode().equals(HttpStatus.OK)) {
            return response.bodyToMono(String.class);
        } else if (response.statusCode().is4xxClientError()) {
            return response.createException().flatMap(Mono::error);
        } else {
            return response.createException()
                    .flatMap(Mono::error);
        }
    }

    private void log(String s) {
        // System.out.println(s);
        logger.info("------------- ------------- " + s);
    }

    public AnalyticsWebClientConfig getAnalyticsWebClientConfig() {
        return analyticsWebClientConfig;
    }

}