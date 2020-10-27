package com.javasampleapproach.springintegration.endpoint;

import com.javasampleapproach.springintegration.msg.HelloMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WelcomeEndpoint
{

    public Message<?> get(Message<String> msg)
    {
        String name = msg.getPayload();
        System.out.println("------------> name： " + name);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now);

        String strMsg = "Hello " + name + " ! "+"Welcome to Spring Integration";
        HelloMsg returnMsg = new HelloMsg(strMsg,currentTime);

        return MessageBuilder.withPayload(returnMsg)
//                              .copyHeadersIfAbsent(msg.getHeaders())
//                              .setHeader("http_statusCode", HttpStatus.OK)
                              .build();




    }

}
