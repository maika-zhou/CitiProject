package com.dao;

import com.pojo.Account;

import java.util.List;

public interface AccountDAO
{
    public List<Account> findAll();

    public Account findById(long uid);

    public int updateAccount(Account account);
}
