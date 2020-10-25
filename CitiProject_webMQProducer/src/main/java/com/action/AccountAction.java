package com.action;


import com.msg.AccountMsg;
import com.pojo.Account;
import com.service.AccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.rocketmq.common.message.Message;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/account")
@RestController
public class AccountAction
{
    @Autowired
    private AccountService accountService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @ApiOperation(value = "testMQ", notes = "testMQ")
    @RequestMapping(value = "testMQ", method = RequestMethod.POST)
    public String testMQ() throws InterruptedException, RemotingException, MQClientException, MQBrokerException
    {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //设置NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //启动Producer实例
        producer.start();


        Message msg1 = new Message("msg_receiver_destination","Batch",("Hello world1").getBytes());
        Message msg2 = new Message("msg_receiver_destination","Batch",("Hello world2").getBytes());
        Message msg3 = new Message("msg_receiver_destination","Batch",("Hello world3").getBytes());
        List<Message> msgs = Arrays.asList(msg1, msg2, msg3);

        SendResult result = producer.send(msgs);
        SendStatus status = result.getSendStatus();
        System.out.println( "发送状态: "+status );

        producer.shutdown();



        return "aaaa";
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "uid", value = "ID", required = true, dataType = "long")})
    @ApiOperation(value = "查询所有账户", notes = "查询所有账户")
    @RequestMapping(value = "findById", method = RequestMethod.POST)
    public Account findById(@RequestBody Long uid)
    {
        Account account = accountService.findById(uid);

        account.setMoney(account.getMoney().subtract(new BigDecimal(200)));

        int val = accountService.updateAccount(account);

        System.out.println("val = " + val);
        return account;
    }

    @ApiOperation(value = "转账", notes = "转账")
    @RequestMapping(value = "transfer", method = RequestMethod.POST)
    public TransactionSendResult transfer(@ModelAttribute AccountMsg msg)
    {
        TransactionSendResult result = accountService.sendMessageForDebit(msg);

        return result;
    }

}
