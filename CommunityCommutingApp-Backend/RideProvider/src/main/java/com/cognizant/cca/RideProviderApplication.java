package com.cognizant.cca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RideProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideProviderApplication.class, args);
	}

}
