package com.orderservice.service.iface;

import com.orderservice.dto.OrderRequest;

public interface OrderService {

     void placeOrder(OrderRequest orderRequest);
}
