package com.book.ov;

import com.book.pojo.Order;
import com.book.pojo.OrderItem;


import java.math.BigDecimal;
import java.util.List;

public class OrderVo {
   private Order order;//订单
    public String orderNo;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Order getOrder() {
        return order;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private List<OrderItem> orderItems;

}
