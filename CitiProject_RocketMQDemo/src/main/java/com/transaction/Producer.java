package com.transaction;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.TimeUnit;

//同步发送消息
public class Producer
{

    public static void main(String[] args) throws Exception
    {
        //1 实例化消者Producer
        TransactionMQProducer producer = new TransactionMQProducer("group1");
        //2 设置NameServer地址
        producer.setNamesrvAddr("10.249.213.132:9876");
        //3 设置事务Listener
        producer.setTransactionListener(new TransactionListener()
        {
            /**
             *  在该方法中执行本地事务
             * @param msg
             * @param arg
             * @return
             */
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg)
            {
                if(StringUtils.equals("Tag0",msg.getTags()))
                {
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                else if(StringUtils.equals("Tag1",msg.getTags()))
                {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                else if(StringUtils.equals("Tag2",msg.getTags()))   //设置UNKNOW,此msg会调用checkLocalTransaction()
                {
                    return LocalTransactionState.UNKNOW;
                }
                return LocalTransactionState.UNKNOW;
            }

            /**
             *  如果迟迟没有响应，该方法是MQ进行消息事务状态回查
             * @param msg
             * @return
             */
            public LocalTransactionState checkLocalTransaction(MessageExt msg)
            {
                System.out.println("Msg Tag:" + msg.getTags());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });


        //4 启动Producer实例
        producer.start();

        String[] tags = {"Tag0","Tag1","Tag2"};
        //5 启动Producer实例
        for (int i = 0; i <3 ; i++) {

            /**
             *  Param1: Topic
             *  Param2: tags
             *  Param3: 消息内容
             */
            Message msg = new Message("Transaction",tags[i],("Hello world"+i).getBytes());
            //发送消息
            SendResult result = producer.sendMessageInTransaction(msg,null);
            //msg ID
            String msgId = result.getMsgId();
            //发送状态
            SendStatus status = result.getSendStatus();
            //消息接收队列ID
            int queueId = result.getMessageQueue().getQueueId();

            System.out.println("发送状态: "+status+",消息ID："+msgId+",QueueID: "+queueId);
            //线程睡眠1秒
            TimeUnit.SECONDS.sleep(1);
        }

        //回查方法，是不能把producer关闭的
        //producer.shutdown();

    }



}
