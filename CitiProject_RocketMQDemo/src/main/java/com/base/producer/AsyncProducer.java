package com.base.producer;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

//异步发送消息
public class AsyncProducer
{

    public static void main(String[] args) throws Exception
    {
        //实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //设置NameServer地址
        producer.setNamesrvAddr("10.249.213.132:9876");
        //启动Producer实例
        producer.start();

        for (int i = 0; i <10 ; i++) {

            /**
             *  Param1: Topic
             *  Param2: tags
             *  Param3: 消息内容
             */
            Message msg = new Message("Base","Async",("Hello world"+i).getBytes());
            //发送异步消息
            producer.send(msg, new SendCallback(){
                public void onSuccess(SendResult sendResult)
                {
                    System.out.println("发送结果： " + sendResult);
                }

                public void onException(Throwable e)
                {
                    System.out.println("发送异常： " + e);
                }
            });


            //线程睡眠1秒
            TimeUnit.SECONDS.sleep(1);
        }
        producer.shutdown();

    }




}
