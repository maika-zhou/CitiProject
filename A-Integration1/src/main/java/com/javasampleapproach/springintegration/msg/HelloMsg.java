package com.javasampleapproach.springintegration.msg;

public class HelloMsg
{
    private String msg;
    private String currentTime;

    public HelloMsg()
    {
    }

    public HelloMsg(String msg, String currentTime)
    {
        this.msg = msg;
        this.currentTime = currentTime;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getCurrentTime()
    {
        return currentTime;
    }

    public void setCurrentTime(String currentTime)
    {
        this.currentTime = currentTime;
    }
}
