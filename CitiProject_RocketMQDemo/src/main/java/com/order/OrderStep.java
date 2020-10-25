package com.order;

import java.util.*;

public class OrderStep
{
    private long orderId;
    private String desc;



    public long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(long orderId)
    {
        this.orderId = orderId;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    @Override
    public String toString()
    {
        return "OrderStep{" +
                "orderId=" + orderId +
                ", desc='" + desc + '\'' +
                '}';
    }

    public OrderStep(long orderId, String desc)
    {
        this.orderId = orderId;
        this.desc = desc;
    }

    public static List<OrderStep> buildOrders()
    {
        //Mock up 3个Order
        List<OrderStep> list = new ArrayList<OrderStep>();
        OrderStep order = new OrderStep(1039L,"创建");
        list.add(order);
        order = new OrderStep(1039L,"付款");
        list.add(order);
        order = new OrderStep(1039L,"完成");
        list.add(order);
        order = new OrderStep(1039L,"推送");
        list.add(order);

        order = new OrderStep(1065L,"创建");
        list.add(order);
        order = new OrderStep(1065L,"付款");
        list.add(order);
        order = new OrderStep(1065L,"完成");
        list.add(order);

        order = new OrderStep(7235L,"创建");
        list.add(order);
        order = new OrderStep(7235L,"完成");
        list.add(order);
        order = new OrderStep(7235L,"推送");
        list.add(order);

        return list;
    }



}
