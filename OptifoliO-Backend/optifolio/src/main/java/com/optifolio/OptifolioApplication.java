package com.optifolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.optifolio")
public class OptifolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(OptifolioApplication.class, args);
	}

}
