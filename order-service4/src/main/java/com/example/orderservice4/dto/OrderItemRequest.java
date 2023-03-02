package com.example.orderservice4.dto;


import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemRequest {
    private Long id;
    private String sku_code;
    private BigDecimal price;
    private Integer quantiry;
}
