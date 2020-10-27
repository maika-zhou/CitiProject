package com;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.dao"})	//这样在DAO里不需要加 @Mapper
public class ProducerStarter
{
    public static void main(String[] args) {
        SpringApplication.run(ProducerStarter.class, args);
    }

}
