package com.msg;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountMsg
{
    private long uid; //转账者 1001
    private BigDecimal money;//1
    private long toUid;//转账给谁 1002
    private String txNo = UUID.randomUUID().toString();//全局事务ID.

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

    public long getToUid()
    {
        return toUid;
    }

    public void setToUid(long toUid)
    {
        this.toUid = toUid;
    }
    public String getTxNo() {
        return txNo;
    }
    public void setTxNo(String txNo) {
        this.txNo = txNo;
    }
}
