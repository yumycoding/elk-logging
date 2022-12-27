package com.yumy.elklogging;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Log4j2
public class LogController {

    String subActivationBasePath = "http://localhost:8307/v1/subscriptions/activate/";
    String subCancelActivationBasePath = "http://localhost:8307/v1/subscriptions/cancel/";

    private final RestTemplate restTemplate;

    public LogController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    @GetMapping(path = "/log")
    public String logging(@RequestParam String subCode) {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(subActivationBasePath)
                .queryParam("subCode", subCode).toUriString();

        ResponseEntity<String> subGatewayResponse;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> subscriptionPayload = new LinkedMultiValueMap<>();
        subscriptionPayload.add("subCode", subCode);
        HttpEntity<?> subscriptionActivations = new HttpEntity<>(subscriptionPayload, headers);

        try {
            subGatewayResponse = restTemplate.postForEntity(urlTemplate, null, String.class);

        } catch (ResourceAccessException e) {
            log.error(e.getMessage());
            throw new ResourceAccessException("cannot process request this time.");
        }
        if (subGatewayResponse.getStatusCode().is2xxSuccessful()) {
            log.info(subGatewayResponse.getBody());
        }

        log.info("second request");

        try {
            subGatewayResponse = restTemplate.postForEntity(subCancelActivationBasePath, subscriptionActivations, String.class);

        } catch (ResourceAccessException e) {
            log.error(e.getMessage());
            throw new ResourceAccessException("cannot process request this time.");
        }
        if (subGatewayResponse.getStatusCode().is2xxSuccessful()) {
            log.info(subGatewayResponse.getBody());
        }

        return "Request Completed";
    }

}


//    ResponseEntity<String> subGatewayResponse;
//    HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//                MultiValueMap<String, String> subscriptionPayload = new LinkedMultiValueMap<>();
//        subscriptionPayload.add("subCode", subCode);
//        HttpEntity<MultiValueMap<String, String>> subscriptionActivations = new HttpEntity<>(subscriptionPayload, headers);
