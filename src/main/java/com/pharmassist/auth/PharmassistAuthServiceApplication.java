package com.pharmassist.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class PharmassistAuthServiceApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("JVM TZ = " + TimeZone.getDefault().getID());
		SpringApplication.run(PharmassistAuthServiceApplication.class, args);
	}

}
