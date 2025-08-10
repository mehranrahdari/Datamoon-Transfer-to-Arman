package com.pedasco.datamoontransfertoarman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TokenService {
    @Value("${external.base-url}")
    private String baseUrl;
    @Value("${external.username}")
    private String username;
    @Value("${external.password}")
    private String password;

    private String token;
//    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private final RestTemplate restTemplate;

    public TokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public synchronized String getToken() {
        if (token == null) fetchToken();
        return token;
    }

    public synchronized void fetchToken() {
        Map<String, String> body = Map.of("username", username, "password", password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        // درخواست به API
        ResponseEntity<Map> response = restTemplate.postForEntity(
                baseUrl + "/users/auth", entity, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // استخراج توکن بدون استفاده از DTO
            Map<String, Object> userMap = (Map<String, Object>) response.getBody().get("user");
            this.token = (String) userMap.get("token");
        } else {
            throw new RuntimeException("Token fetch failed!");
        }
    }


    public synchronized void invalidateToken() {
        token = null;
    }
}
