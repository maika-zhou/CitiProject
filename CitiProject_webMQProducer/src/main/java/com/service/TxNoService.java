package com.service;

import com.dao.AccountDAO;
import com.dao.TxNoDAO;
import com.msg.AccountMsg;
import com.pojo.Account;
import com.pojo.TxNo;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TxNoService
{

    @Autowired
    private TxNoDAO txNoDAO;

    public boolean isExist(String txNo)
    {
        TxNo vo = txNoDAO.findByTxNo(txNo);

        return vo==null? false:true;
    }
}

