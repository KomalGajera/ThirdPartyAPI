package com.demo.usefulldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UsefullDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsefullDemoApplication.class, args);
	}

}
