package com.springcamel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.springcamel")
@EnableAutoConfiguration
public class CamelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelApplication.class, args);
	}

	
}
