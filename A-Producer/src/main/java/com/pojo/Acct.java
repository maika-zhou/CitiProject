package com.pojo;


public class Acct
{
    private long id;
    private String cardNumber;
    private long balance;


    public Acct()
    {
    }

    public Acct(long id, String cardNumber, long balance)
    {
        this.id = id;
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public long getBalance()
    {
        return balance;
    }

    public void setBalance(long balance)
    {
        this.balance = balance;
    }



    @Override
    public String toString()
    {
        return "Acct{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
