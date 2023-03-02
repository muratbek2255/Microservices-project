package com.example.orderservice4.service;

import com.example.orderservice4.dto.OrderItemRequest;
import com.example.orderservice4.entity.OrderItems;

public interface OrderPayment {

    public OrderItems mapToDto(OrderItemRequest orderItemRequest);
}
