package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class ProviderApp
{
	public static void main(String[] args) {
		SpringApplication.run(ProviderApp.class, args);
	}
}
