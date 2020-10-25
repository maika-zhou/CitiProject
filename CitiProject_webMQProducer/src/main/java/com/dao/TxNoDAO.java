package com.dao;

import com.pojo.TxNo;


public interface TxNoDAO
{

    public TxNo findByTxNo(String txNo);

    public void insert(TxNo txNo);
}
