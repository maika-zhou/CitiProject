package com.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Producer
{
    public static void main(String[] args) throws Exception
    {
        //实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //设置NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //启动Producer实例
        producer.start();

        List<OrderStep> list = OrderStep.buildOrders();

        for (int i = 0; i < list.size(); i++)
        {
            Message msg = new Message("Base","Order","i"+i,list.get(i).toString().getBytes());
            /**
             *  Param1: 消息内容
             *  Param2: 消息队列的选择器
             *  Param3: 选择队列的业务标识（订单ID）
             */
            SendResult sendResult = producer.send(msg, new MessageQueueSelector()
            {
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg)
                {
                    long orderID = (Long)arg;

                    long index = orderID%mqs.size();
                    System.out.println("mqsize = " + mqs.size()+" ，index："+index);
                    return mqs.get((int)index);
                }
            }, list.get(i).getOrderId());

            System.out.println("发送结果 = " + sendResult);
        }

        producer.shutdown();
    }
}
