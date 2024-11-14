package com.microservice.crops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceCropsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCropsApplication.class, args);
	}

}
