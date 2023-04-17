package com.mana.sample.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="Rest API Example Project"))
public class RestAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestAPIApplication.class);
	}

}
