package com.out.android;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class AndroidApplication {

	public static void main(String[] args) {
		SpringApplication.run(AndroidApplication.class, args);
	}

}
