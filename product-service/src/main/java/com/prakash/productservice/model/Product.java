package com.prakash.productservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
@Data
@Builder
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;


}
