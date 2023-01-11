package com.orderservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDto {
    private Integer id;
    private String skuCode;
    private double price;
    private int quantity;
}
