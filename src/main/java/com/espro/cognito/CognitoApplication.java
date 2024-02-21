package com.espro.cognito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class CognitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CognitoApplication.class, args);
	}

}
