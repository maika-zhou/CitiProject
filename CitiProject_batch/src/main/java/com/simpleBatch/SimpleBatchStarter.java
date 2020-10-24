package com.simpleBatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableBatchProcessing
@SpringBootApplication
public class SimpleBatchStarter {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SimpleBatchStarter.class, args);
    }

}
