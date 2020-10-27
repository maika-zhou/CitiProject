package com.dao;


import com.pojo.Acct;
import com.pojo.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcctDAO
{
    public List<Acct> findAll();


    public Acct findById(long id);

    public int updateAcct(Acct acct);

}