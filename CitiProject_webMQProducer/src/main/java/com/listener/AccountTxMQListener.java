package com.listener;

import com.msg.AccountMsg;
import com.pojo.Account;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.service.AccountService;
import org.springframework.transaction.annotation.Transactional;
@Component
@RocketMQTransactionListener(txProducerGroup = "producer_tx_group")
public class AccountTxMQListener implements RocketMQLocalTransactionListener
{

	@Autowired
	private AccountService accountService;

	/**
	 * 执行本地事务的方法.
	 */
	@Override
    @Transactional
	public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg)
	{
		try
		{
			String str = new String((byte[]) msg.getPayload());// object是byte[]数组类型
			System.out.println("执行本地事务的方法--------->"+str);
			// str 是一个json字符串.
			AccountMsg accountMsg = JSON.parseObject(str, AccountMsg.class);

			accountService.debit(accountMsg);
			
			return RocketMQLocalTransactionState.COMMIT;
		}
		catch(Exception e)
		{
			System.out.println("MQ Rollback");
			e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
		}
	}

	/**
	 * 事务回查的方法.
	 */
	public RocketMQLocalTransactionState checkLocalTransaction(Message msg)
	{
		String str = new String((byte[]) msg.getPayload());// object是byte[]数组类型
		System.out.println("事务回查的方法--------->"+str);
		// str 是一个json字符串.
//		AccountChangeEvent event = JSON.parseObject(str, AccountChangeEvent.class);
//		if (txNoRepository.existsById(event.getTxNo()))
//		{
//			return RocketMQLocalTransactionState.COMMIT;
//		}
		return RocketMQLocalTransactionState.UNKNOWN;
	}

}
