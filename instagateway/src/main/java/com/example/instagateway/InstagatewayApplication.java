package com.example.instagateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InstagatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstagatewayApplication.class, args);
	}

}
