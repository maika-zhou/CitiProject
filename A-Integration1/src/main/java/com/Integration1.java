package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:integration.xml")
public class Integration1
{
	public static void main(String[] args) {
		SpringApplication.run(Integration1.class, args);
	}
}
