package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PublicApiController {

    @Autowired
    private PublicApiService publicApiService;

    @GetMapping("/data")
    public String getData() {
        return publicApiService.getData();
    }
}