package com.example.orderservice4.controller;


import com.example.orderservice4.dto.OrderRequest;
import com.example.orderservice4.service.OrderPaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderPaymentServiceImpl orderPaymentService;

    @Autowired
    public OrderController(OrderPaymentServiceImpl orderPaymentService) {
        this.orderPaymentService = orderPaymentService;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {

        orderPaymentService.placeOrder(orderRequest);

        return ResponseEntity.status(201).body("Order Placed Successfully");
    }
}
