package com.pharmassist.auth;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.TimeZone;

@SpringBootApplication
public class PharmassistAuthServiceApplication {

	@Value("${jwt.secret}")
	private String secret;

	public static void main(String[] args) {
//		System.out.println("JWT SECRET LOADED: " + (new PharmassistAuthServiceApplication().secret != null) +"-> "+ System.getenv("JWT_SECRET"));
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("JVM TZ = " + TimeZone.getDefault().getID());
		SpringApplication.run(PharmassistAuthServiceApplication.class, args);
	}

	@PostConstruct
	public void checkJwtSecret() {
//		System.out.println("JWT SECRET LOADED: " + (secret != null));
//		System.out.println("JWT SECRET (env): " + System.getenv("JWT_SECRET"));

		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("JVM TZ = " + TimeZone.getDefault().getID());
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
