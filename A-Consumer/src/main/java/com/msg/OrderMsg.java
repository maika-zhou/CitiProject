package com.msg;

public class OrderMsg
{
    private long id;        //Account ID
    private long pid;       //Product ID
    private long quantity;  //Shopping quantity;

    private long paymentAmt;


    public OrderMsg()
    {
    }

    public OrderMsg(long id, long pid, long quantity, long paymentAmt)
    {
        this.id = id;
        this.pid = pid;
        this.quantity = quantity;
        this.paymentAmt = paymentAmt;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getPid()
    {
        return pid;
    }

    public void setPid(long pid)
    {
        this.pid = pid;
    }

    public long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(long quantity)
    {
        this.quantity = quantity;
    }


    public long getPaymentAmt()
    {
        return paymentAmt;
    }

    public void setPaymentAmt(long paymentAmt)
    {
        this.paymentAmt = paymentAmt;
    }

    @Override
    public String toString()
    {
        return "OrderMsg{" +
                "id=" + id +
                ", pid=" + pid +
                ", quantity=" + quantity +
                ", paymentAmt=" + paymentAmt +
                '}';
    }
}
