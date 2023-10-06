package com.orderservice.controller;

import com.orderservice.dto.OrderRequest;
import com.orderservice.service.iface.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

   @PostMapping("/place")
   @CircuitBreaker(name ="inventory", fallbackMethod = "fallbackMethod")
   @TimeLimiter(name="inventory")
   @Retry(name = "inventory")
   @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
    return CompletableFuture.supplyAsync(()->"Order Placed Successfully") ;
    }

    public CompletableFuture <String> fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Some error with order service, try again in a few minutes");
    }

}
/*
Add the Spring Cloud Circuit Breaker dependency to your project.

Annotate the method that you want to protect with the @HystrixCommand annotation. You can specify a fallback method to be called when the circuit breaker trips.

Configure the circuit breaker properties in your application.properties or application.yml file, such as the threshold for the number of failures before tripping the circuit.

Enable the circuit breaker support by adding the @EnableCircuitBreaker annotation to your main application class or by using the spring-cloud-starter-circuitbreaker.

Use the appropriate monitoring tool to monitor the circuit breaker status and metrics.

You can also use the @HystrixProperty annotation to configure properties for individual methods.
 */