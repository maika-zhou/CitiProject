package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@SpringBootApplication
@ImportResource("http-outbound-gateway.xml")
public class HttpApplication {

//    @Autowired
//    @Qualifier("get_send_channel")
//    MessageChannel getSendChannel;
//
//    @Autowired
//    @Qualifier("get_receive_channel")
//    PollableChannel getReceiveChannel;

    public static void main(String[] args) {
        SpringApplication.run(HttpApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            Message message = MessageBuilder.withPayload("").build();
//            getSendChannel.send(message);
//            System.out.println(getReceiveChannel.receive().getPayload());
//        };
//    }
}