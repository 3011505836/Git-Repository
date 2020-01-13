package com.fh.service;


import com.fh.common.ServerResponse;
import com.fh.model.Order;

public interface OrderService {
    ServerResponse addOrder(Long uuid, Integer id);

    void updateOrder(Order order);
}
