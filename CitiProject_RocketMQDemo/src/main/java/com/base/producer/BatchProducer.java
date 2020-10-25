package com.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BatchProducer
{
    //不像之前通过for循环发送，这里是发送一个List，这里的发送是异步的
    public static void main(String[] args) throws Exception
    {
        //实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //设置NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //启动Producer实例
        producer.start();


        Message msg1 = new Message("Base","Batch",("Hello world1").getBytes());
        Message msg2 = new Message("Base","Batch",("Hello world2").getBytes());
        Message msg3 = new Message("Base","Batch",("Hello world3").getBytes());
        List<Message> msgs = Arrays.asList(msg1, msg2, msg3);
        //发送一个msg List
        SendResult result = producer.send(msgs);
        SendStatus status = result.getSendStatus();
        System.out.println( "发送状态: "+status );

        producer.shutdown();
    }


}
