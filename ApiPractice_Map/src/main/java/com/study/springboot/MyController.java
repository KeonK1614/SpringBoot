package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class MyController {
	@Autowired
	private MyService myService;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Basic";
	}
	
	@RequestMapping("/maptest")
	public String test(Model model) {
		try {
			JsonNode locationData = myService.getLocationData();
			double latitude = locationData.path("latitude").asDouble();
			double longitude = locationData.path("longitude").asDouble();
			model.addAttribute("latitude", latitude);
			model.addAttribute("longitude", longitude);
			return "maptest";
		} catch (Exception e) {
			model.addAttribute("error", "Failed to get location data.");
			return "error";
		}
		
	} 
	

}
