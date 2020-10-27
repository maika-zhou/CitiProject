package com.service;

import com.dao.TxNoDAO;
import com.pojo.TxNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

