package com.javne.dentalprofitapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.javne.dentalprofitapp.model")
public class DentalProfitAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(DentalProfitAppApplication.class, args);
	}
}
