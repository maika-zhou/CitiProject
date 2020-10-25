package com;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(basePackages = {"com.dao"})	//这样在DAO里不需要加 @Mapper
@SpringBootApplication
//@EnableDiscoveryClient //启用注册中心客户端，能够访问到eureka注册中心。
//@EnableFeignClients
public class WebMQConsumerStarter
{
	public static void main(String[] args) {
		SpringApplication.run(WebMQConsumerStarter.class, args);
	}
}
