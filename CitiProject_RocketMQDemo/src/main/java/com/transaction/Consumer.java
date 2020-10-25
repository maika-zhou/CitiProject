package com.transaction;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer
{
    public static void main(String[] args) throws Exception
    {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        consumer.setNamesrvAddr("10.249.213.132:9876");
        consumer.subscribe("Transaction","*");

        consumer.registerMessageListener(new MessageListenerConcurrently()
        {
            //接收msg
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context)
            {
                //System.out.println("msgs = " + msgs);

                for (MessageExt msg : msgs) {
                    System.out.println( new String(msg.getBody()) );
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });


        consumer.start();
        System.out.println("Consumer starting......");
    }


}
