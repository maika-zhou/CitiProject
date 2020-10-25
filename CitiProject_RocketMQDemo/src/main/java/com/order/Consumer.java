package com.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer
{
    public static void main(String[] args) throws Exception
    {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("Base","Order");

        consumer.registerMessageListener(new MessageListenerOrderly()
             {
                 public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context)
                 {
                     for (MessageExt msg : msgs) {
                         System.out.println( "线程 = " +Thread.currentThread().getName()+ " ：" + new String(msg.getBody()));
                     }
                     return ConsumeOrderlyStatus.SUCCESS;
                 }
             }
        );

        consumer.start();
        System.out.println("Consumer Starting....");
    }

}
