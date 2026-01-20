package com.pharmassist.auth;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.TimeZone;

@SpringBootTest
class PharmassistAuthServiceApplicationTests {

	@BeforeAll
	static void setUpTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
	}


	@Test
	void contextLoads() {
	}

}
