package com.orderservice.controller;

import com.orderservice.dto.OrderRequest;
import com.orderservice.service.iface.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    @CircuitBreaker(name ="inventory", fallbackMethod = "fallbackMethod")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
    return "Order Placed Successfully";
    }

    public String fallbackMethod(OrderRequest orderRequest){

        return "Some error with order service";
    }



}
