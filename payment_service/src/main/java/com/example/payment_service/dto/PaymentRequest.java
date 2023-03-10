package com.example.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
public class PaymentRequest {

    private BigDecimal price;

    private String accountCheck;
}
