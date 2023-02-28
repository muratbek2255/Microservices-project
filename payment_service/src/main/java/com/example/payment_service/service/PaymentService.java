package com.example.payment_service.service;


import com.example.payment_service.dto.PaymentRequest;

public interface PaymentService {

    public String addStatus(PaymentRequest paymentRequest);

    public String setStatus(int id);

    public String rollbackPayment(int id);

    public String getByStatus(String status);
}
