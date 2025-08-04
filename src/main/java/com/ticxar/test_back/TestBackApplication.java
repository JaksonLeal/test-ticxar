package com.ticxar.test_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.ticxar.test_back.feign")

public class TestBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestBackApplication.class, args);
	}

}
