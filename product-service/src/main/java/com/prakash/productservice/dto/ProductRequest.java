package com.prakash.productservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest {

    private String name;
    private String description;
    private double price;
}
