package com.service;

import java.math.BigDecimal;

import com.dao.AccountDAO;
import com.msg.AccountMsg;
import com.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountService
{

	@Autowired
	private AccountDAO accountDAO;


	/**
	 * 
	 */
	@Transactional
	public int increase(AccountMsg msg)
	{
		// 增加金额
		Account account = accountDAO.findById(msg.getToUid());
		if (account == null)
		{
			throw new RuntimeException("Account is not exist");
		}

		account.setMoney(account.getMoney().add(msg.getMoney()));
		int result = accountDAO.updateAccount(account);

		// 扣减金额，判断如果是张三的id = 1001的时候，不可操作，抛出异常，为了方便测试。
//		if (event.getToUid() == 1001)
//		{
//			// 强制抛出. 为了测试本地事务是否可以回滚。
//			throw new RuntimeException("Account is invalid,uid=" + event.getToUid());
//		}
//
//		txNoRepository.save(new TxNo2(event.getTxNo()));

		return result;
	}
}
