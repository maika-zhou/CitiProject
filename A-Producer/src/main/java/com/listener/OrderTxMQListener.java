package com.listener;

import com.alibaba.fastjson.JSON;
import com.msg.OrderMsg;
import com.pojo.Acct;
import com.service.AcctService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RocketMQTransactionListener(txProducerGroup = "producer_tx_group")
public class OrderTxMQListener implements RocketMQLocalTransactionListener
{

	@Autowired
	private AcctService acctService;

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
			OrderMsg orderMsg = JSON.parseObject(str, OrderMsg.class);
			System.out.println("orderMsg = " + orderMsg);
			//Save msg into DB
			//TO DO...


			//Payment Activity
			acctService.debit(orderMsg);

			//Commit MQ
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
		OrderMsg orderMsg = JSON.parseObject(str, OrderMsg.class);
		System.out.println("事务回查的方法--------->"+str);


		if(orderMsg.tryCount-- == 0)
			return RocketMQLocalTransactionState.COMMIT;
		else
			return RocketMQLocalTransactionState.UNKNOWN;
	}

}
