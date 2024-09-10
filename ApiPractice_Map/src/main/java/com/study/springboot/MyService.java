package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MyService {
	@Autowired
	private RestTemplate restTemplate;
	private static final String apiKey = "41454b73586b656f3532696e475a55";
	private static String serviceName = "tbTraficElvtr";//서울시 지하철역 엘리베이터 위치 정보(좌표계: WGS84)
	private static final String apiUrl = "http://openapi.seoul.go.kr:8088/" + apiKey + "/json/" + serviceName + "/1/5";
	
	public JsonNode getLocationData() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			String jsonResponse = response.getBody();
			ObjectMapper objMapper = new ObjectMapper();
			JsonNode rootNode = objMapper.readTree(jsonResponse);
			
			JsonNode locations = rootNode.path(serviceName).path("row");
			
			if (locations.isArray() && locations.size() > 0) {
				JsonNode location = locations.get(0);
				double latitude = location.path("POINT()").asDouble();
				double longitude = location.path("LNG").asDouble();
				
				ObjectMapper resultMapper = new ObjectMapper();
				ObjectNode result = resultMapper.createObjectNode();
				System.out.println(latitude);
				System.out.println(longitude);
				result.put("latitude", latitude);
				result.put("longitude", longitude);
				
				return result;
			}
			throw new RuntimeException("No location data available");
		} else {
			throw new RuntimeException("Failed to get data: " + response.getStatusCode());
		}
	}
}
