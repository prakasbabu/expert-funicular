package com.inventoryservice.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name ="t_inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String skuCode;
    private int quantity;



}
