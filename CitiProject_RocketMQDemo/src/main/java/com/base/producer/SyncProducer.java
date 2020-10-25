package com.base.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

//同步发送消息
public class SyncProducer
{

    public static void main(String[] args) throws Exception
    {
        //实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //设置NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //启动Producer实例
        producer.start();

        for (int i = 0; i <10 ; i++) {

            /**
             *  Param1: Topic
             *  Param2: tags
             *  Param3: 消息内容
             */
            Message msg = new Message("Base","Sync",("Hello world"+i).getBytes());
            //发送消息
            SendResult result = producer.send(msg);
            //msg ID
            String msgId = result.getMsgId();
            //发送状态
            SendStatus status = result.getSendStatus();
            //消息接收队列ID
            int queueId = result.getMessageQueue().getQueueId();

            System.out.println("发送状态: "+status+",Msg ID："+msgId+",QueueID: "+queueId);
            //线程睡眠1秒
            TimeUnit.SECONDS.sleep(1);
        }
        producer.shutdown();

    }



}
