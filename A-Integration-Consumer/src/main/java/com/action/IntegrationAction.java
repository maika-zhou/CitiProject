package com.action;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IntegrationAction
{

    @Autowired
    @Qualifier("get_receive_channel")
    PollableChannel receivedChannel;

    @Autowired
    @Qualifier("get_send_channel")
    MessageChannel getRequestChannel;


    @ApiOperation(value = "下单",notes = "下单")
    @RequestMapping(value = "/placeOrder",method = RequestMethod.POST)
    public String  placeOrder()
    {

        //Message<OrderMsg> message = MessageBuilder.withPayload(orderMsg).build();
//        Map<String,Integer> map = new HashMap<>();
//        map.put("id",1002);

        Message<?> message = MessageBuilder.withPayload("").build();

        getRequestChannel.send(message);
        Message<?> receivedMsg = receivedChannel.receive();



        receivedMsg.getPayload();
        System.out.println("---------->"+ receivedMsg.getPayload() );
//        ServerMsg serverMsg = (ServerMsg) receivedMsg.getPayload();
//        System.out.println("############## ServerMsg ##############");
//        System.out.println(serverMsg.toString());
//        System.out.println("############## Done! ##############");





        return "";
    }



}
