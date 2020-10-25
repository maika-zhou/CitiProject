package com.pojo;

import java.math.BigDecimal;

public class Account
{
    private long uid;
    private BigDecimal money;

    public long getUid()
    {
        return uid;
    }

    public void setUid(long uid)
    {
        this.uid = uid;
    }

    public BigDecimal getMoney()
    {
        return money;
    }

    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    @Override
    public String toString()
    {
        return "Account{" +
                "uid=" + uid +
                ", money=" + money +
                '}';
    }
}
