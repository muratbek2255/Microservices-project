package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
public class ProductRequest {
    private String id;

    private String name;

    private String description;

    private BigDecimal price;
}
