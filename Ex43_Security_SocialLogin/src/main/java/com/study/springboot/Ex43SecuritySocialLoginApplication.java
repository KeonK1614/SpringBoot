package com.study.springboot;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Ex43SecuritySocialLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex43SecuritySocialLoginApplication.class, args);
	}

}
