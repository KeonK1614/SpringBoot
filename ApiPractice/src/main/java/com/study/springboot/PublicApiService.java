package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class PublicApiService {

    @Autowired
    private RestTemplate restTemplate;
    private static final String API_KEY = "41454b73586b656f3532696e475a55";

    private static final String API_URL = "http://openapi.seoul.go.kr:8088/" + API_KEY + "/json/tbTraficElvtr/1/5/";
    		
    public String getData() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to get data: " + response.getStatusCode());
        }
    }
}