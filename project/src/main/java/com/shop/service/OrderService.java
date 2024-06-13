package com.shop.service;

import com.shop.model.Order;

import java.util.List;

public interface OrderService {
    Boolean create(Order order);
    List<Order> getByUserId();
}
