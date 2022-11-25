package com.apba.proas.backend.controller.analytics;

import java.time.Duration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AnalyticsWebClientConfig analyticsWebClientConfig;

    // no es Bean xq no crea objetos. Spring no usa este metodo
    public AnalyticsWebClientConfig getAnalyticsWebClientConfig() {
        return analyticsWebClientConfig;
    }

    Logger logger;

    @PostConstruct
    public void init() {
        logger = LoggerFactory.getLogger(AnalyticsWebClient.class);
        if (analyticsWebClientConfig == null) {
            logger.error("AnalyticsWebclient:AnalyticsWebClientConfig NO SE HA INICIALIZADO DE FICHERO");
        }
        log("AnalyticsWebClient inicializado", "ok");
    }

    public AnalyticsWebClient() {
        super();
    }

    public String getTest() {
        String x = getResponse(analyticsWebClientConfig.getUriTest())
                .bodyToMono(String.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
        log(analyticsWebClientConfig.getUriConfig(), x);
        return x;
    }

    public ProasBackendConfig getConfig() {
        ProasBackendConfig x = getResponse(analyticsWebClientConfig.getUriConfig())
                .bodyToMono(ProasBackendConfig.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
        log(analyticsWebClientConfig.getUriConfig(), x);
        return x;
    }

    public AoiState getSecurityIndicator(@PathVariable int id) {
        AoiState x = getResponse(analyticsWebClientConfig.getUriSecurity(), id)
                .bodyToMono(AoiState.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
        log(analyticsWebClientConfig.getUriSecurity(), x);
        return x;
    }

    public ResponseSpec getResponse(String uri, Object... objects) {
        ResponseSpec rs = WebClient
                .create(analyticsWebClientConfig.getUrl() + ":" + analyticsWebClientConfig.getPort()
                        + analyticsWebClientConfig.getApplication())
                .get()
                .uri(uri, objects)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();
        return rs;
    }

    public Vessel getVesselComplejo() {
        Vessel x = getResponseComplejo(analyticsWebClientConfig.getUriVessel())
                .bodyToMono(Vessel.class)
                .block(Duration.ofSeconds(analyticsWebClientConfig.getTimeout()));
        return x;
    }

    public ResponseSpec getResponseComplejo(String uri, Object... objects) {
        WebClient webClient = WebClient
                .builder()
                .baseUrl(analyticsWebClientConfig.getUrl() + ":" + analyticsWebClientConfig.getPort()
                        + analyticsWebClientConfig.getApplication())
                .build();

        RequestBodyUriSpec bodySpec = webClient.method(HttpMethod.GET);
        bodySpec.accept(MediaType.APPLICATION_JSON);
        bodySpec.uri(uri, objects);
        bodySpec.uri(uriBuilder -> uriBuilder
                .path(uri) // Se especifica el path con posibles prametros
                .queryParam("par1", "miname")
                .queryParam("par2", "misurname")
                .build(objects)); // Se le pasan los parámetros del path

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

    private void log(String s, Object x) {
        // System.out.println(s);
        logger.info("-------------WebClient::  " + s + "--> " + x);
    }

}