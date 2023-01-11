package com.orderservice.dto;

import com.orderservice.model.OrderLineItems;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderRequest {

    private List<OrderLineItemsDto> orderLineItemsDto;


}
