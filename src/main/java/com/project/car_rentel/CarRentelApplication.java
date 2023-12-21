package com.project.car_rentel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

public class CarRentelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentelApplication.class, args);
	}

}
