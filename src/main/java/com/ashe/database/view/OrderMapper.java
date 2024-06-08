package com.ashe.database.view;

import com.ashe.database.domain.entity.Order;
import com.ashe.database.view.response.OrderRsp;

public class OrderMapper {
    public static OrderRsp OrderRsp(Order order) {
        OrderRsp rsp = new OrderRsp();
        rsp.setOrderId(order.getOrderId());
        rsp.setOrderDate(order.getOrderDate());
        rsp.setCurrency(order.getCurrency());
        rsp.setOrderDate(order.getOrderDate());
        rsp.setCreatedAt(order.getCreatedAt());
        rsp.setUpdatedAt(order.getUpdatedAt());
        return rsp;
    }
}
