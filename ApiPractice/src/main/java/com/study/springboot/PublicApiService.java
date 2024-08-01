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
    private static final String API_KEY = "$2a$10$awTAwYYxOBZZVdftW.UQX.KnbG0SIO6tkPEfl.785K7p/.xpaXdiO";

    private static final String API_URL = "https://openapi.kric.go.kr/openapi/vulnerableUserInfo/stationDisabledToilet?serviceKey=" + API_KEY + "&format=xml&railOprIsttCd=S1&lnCd=3&stinCd=322";
    		
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