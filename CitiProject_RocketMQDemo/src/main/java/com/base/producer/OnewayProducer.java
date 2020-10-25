package com.base.producer;


import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;

//不关心发送结果的场景，eg: 日志发送
public class OnewayProducer
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
            Message msg = new Message("Base","Oneway",("Hello world"+i).getBytes());
            //发送 One way 消息
            producer.sendOneway(msg);


            //线程睡眠1秒
            TimeUnit.SECONDS.sleep(1);
        }
        producer.shutdown();
    }
}
