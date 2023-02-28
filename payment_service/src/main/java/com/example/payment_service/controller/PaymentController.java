package com.example.payment_service.controller;

import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<String> addPayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.status(201).body(paymentService.addStatus(paymentRequest));
    }

    @PutMapping("/setStatus/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable int id) {
        return ResponseEntity.status(201).body(paymentService.setStatus(id));
    }

    @PutMapping("/rollbackStatus/{id}")
    public ResponseEntity<String> rollbackStatus(@PathVariable int id) {
        return ResponseEntity.status(201).body(paymentService.rollbackPayment(id));
    }

    @GetMapping("/status")
    public ResponseEntity<String> getByStatus(@Param("status") String status) {
        return ResponseEntity.status(200).body(paymentService.getByStatus(status));
    }
}
