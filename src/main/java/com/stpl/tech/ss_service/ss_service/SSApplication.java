package com.stpl.tech.ss_service.ss_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SSApplication {

	public static void main(String[] args) {
		SpringApplication.run(SSApplication.class, args);
	}

}
