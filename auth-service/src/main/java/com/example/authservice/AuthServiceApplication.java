package com.example.authservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

	@Value("${spring.rabbitmq.host}")
	String host;
	@Value("${spring.rabbitmq.username}")
	String username;
	@Value("${spring.rabbitmq.password}")
	String password;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}
