package com.example.orderservice4.dto;


import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemRequest {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantiry;
}
