package com;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.dao"})	//这样在DAO里不需要加 @Mapper
@SpringBootApplication
public class ConsumerStarter
{
	public static void main(String[] args) {
		SpringApplication.run(ConsumerStarter.class, args);
	}
}
