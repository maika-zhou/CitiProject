package com.dto;

import io.swagger.annotations.ApiModelProperty;

public class OrderReqDTO
{
    private long id;
    private long pid;
    private long quantity;

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
}
