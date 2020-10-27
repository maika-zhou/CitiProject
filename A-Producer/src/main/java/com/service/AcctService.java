package com.service;

import com.dao.AcctDAO;
import com.dao.ProductDAO;
import com.dao.TxNoDAO;
import com.msg.OrderMsg;
import com.pojo.Acct;
import com.pojo.Product;
import com.pojo.TxNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AcctService
{
    @Autowired
    private AcctDAO acctDAO;

    @Autowired
    private TxNoDAO txNoDAO;

    public List<Acct> findAll()
    {
        return acctDAO.findAll();
    }

    public Acct findById(long id)
    {
        return acctDAO.findById(id);
    }

    public int updateAcct(Acct acct)
    {
        return acctDAO.updateAcct(acct);
    }


    @Transactional
    public int debit(OrderMsg orderMsg)
    {
        // 实现幂等性
        TxNo vo = txNoDAO.findByTxNo(orderMsg.getTxNo());
        if (vo!=null)
        {
            throw new RuntimeException("Request has already been raised");
        }



        Acct acct = acctDAO.findById(orderMsg.getId());
        acct.setBalance( acct.getBalance()-orderMsg.getPaymentAmt() );
        int result = acctDAO.updateAcct(acct);

        // 保存TxNo，这样可以保证幂等性
        TxNo txNoVO = new TxNo();
        txNoVO.setTxNo(orderMsg.getTxNo());
        txNoDAO.insert(txNoVO);


        return result;
    }


}
