package com.microservice.APIRegistryService.APIRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRegistryApplication.class, args);
	}

}
