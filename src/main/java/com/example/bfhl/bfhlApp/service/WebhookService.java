package com.example.bfhl.bfhlApp.service;

import com.example.bfhl.bfhlApp.dto.WebhookRequest;
import com.example.bfhl.bfhlApp.dto.WebhookResponse;
import com.example.bfhl.bfhlApp.utils.HttpUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String regNo = "0827CS221156";
    private final String name = "Manas Purohit";
    private final String email = "manaspurohit220690@acropolis.in";

    public void processWorkflow() {

        String generateUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

//        https :// bfhldevapigw . healthrx . co . in / hiring / generateWebhook / JAVA

        WebhookRequest requestPayload = new WebhookRequest(name, regNo, email);
        HttpEntity<WebhookRequest> requestEntity = new HttpEntity<>(requestPayload, HttpUtils.getJsonHeaders());

        ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(
                generateUrl, requestEntity, WebhookResponse.class
        );

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            System.err.println("Failed to retrieve webhook info.");
            return;
        }

        System.out.println(response);

        String webhookUrl = response.getBody().getWebhookUrl();
        String token = response.getBody().getAccessToken();

        System.out.println("Webhook: " + webhookUrl);
        System.out.println("Access Token: " + token);

//        String finalQuery = "SELECT name FROM employees WHERE department_id = 3;"; // Example only
//
//        HttpHeaders headers = HttpUtils.getJsonHeaders();
//        headers.setBearerAuth(token);
//
//        Map<String, String> answerPayload = Map.of("finalQuery", finalQuery);
//        HttpEntity<Map<String, String>> submission = new HttpEntity<>(answerPayload, headers);
//
//        ResponseEntity<String> result = restTemplate.postForEntity(webhookUrl, submission, String.class);
//
//        if (result.getStatusCode().is2xxSuccessful()) {
//            System.out.println("Solution submitted successfully.");
//        } else {
//            System.err.println("Failed to submit solution. Response: " + result.getStatusCode());
//        }
    }
}
