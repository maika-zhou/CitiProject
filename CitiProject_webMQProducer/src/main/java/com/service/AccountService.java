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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService
{

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private TxNoDAO txNoDAO;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public List<Account> findAll()
    {
        return accountDAO.findAll();
    }

    public Account findById(long uid)
    {
        return accountDAO.findById(uid);
    }

    public int updateAccount(Account account)
    {
        return accountDAO.updateAccount(account);
    }

    /**
     * debit方法需要有事务
     */
    @Transactional
    public int debit(AccountMsg msg)
    {
        // 实现幂等性
        TxNo vo = txNoDAO.findByTxNo(msg.getTxNo());
        if (vo!=null)
        {
            throw new RuntimeException("Transfer request has already been raised");
        }



        Account account = accountDAO.findById(msg.getUid());
        account.setMoney(account.getMoney().subtract(msg.getMoney()));
        int result = accountDAO.updateAccount(account);

        // 保存TxNo，这样可以保证幂等性
        TxNo txNoVO = new TxNo();
        txNoVO.setTxNo(msg.getTxNo());
        txNoDAO.insert(txNoVO);

        // 扣减金额=200的时候，抛出异常，为了方便测试。
        if (msg.getMoney().equals(new BigDecimal(200)))
        {
            // 强制抛出. 为了测试本地事务是否可以回滚。
            throw new RuntimeException("200 为无效扣减金额");
        }

        return result;
    }



    public TransactionSendResult sendMessageForDebit(AccountMsg msg)
    {

        System.out.println( "------------>"+rocketMQTemplate.getProducer().getNamesrvAddr() );
        rocketMQTemplate.getProducer().setVipChannelEnabled(false);

        /**
         *  Basic 判断
         *
         */
        Account account = accountDAO.findById(msg.getUid());
        if (account == null)
        {
            throw new RuntimeException("Account is not exist");
        }
        if (account.getMoney().compareTo(msg.getMoney()) == -1)
        {
            throw new RuntimeException("Money is not enough");
        }


        /*
         * txProducerGroup: 事务组ID,  让程序知道调用哪个类的executeLocalTransaction
         * destination : topic,
         * message: 事务消息
         * arg: 扩展参数
         */
        Message<AccountMsg> message = MessageBuilder.withPayload(msg).build();
        //rocketMQTemplate.sendMessageInTransaction("topic_tansfer", message, null);

        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction("producer_tx_group", "msg_receiver_destination", message, null);
        return result ;
    }




}

