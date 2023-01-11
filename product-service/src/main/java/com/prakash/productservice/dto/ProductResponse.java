package com.prakash.productservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private double price;
}
