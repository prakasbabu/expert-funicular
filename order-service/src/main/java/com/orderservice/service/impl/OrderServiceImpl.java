package com.orderservice.service.impl;

import com.orderservice.dto.InventoryResponse;
import com.orderservice.dto.OrderLineItemsDto;
import com.orderservice.dto.OrderRequest;
import com.orderservice.event.OrderPlacedEvent;
import com.orderservice.model.Order;
import com.orderservice.model.OrderLineItems;
import com.orderservice.repository.OrderRepository;
import com.orderservice.service.iface.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;


    @Override
    public String placeOrder(OrderRequest orderRequest) {

        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());

      List<OrderLineItems> orderLineItemsList =  orderRequest.getOrderLineItemsDto().stream().map(
                orderLineItemsDto -> mapToDto(orderLineItemsDto)
        ).collect(Collectors.toList());

      order.setOrderLineItemsList(orderLineItemsList);


      //get the sku code list
            List<String> skuCodes= orderLineItemsList.stream()
                    .map(orderLineItems -> orderLineItems.getSkuCode()).toList();

            //
          InventoryResponse [] inventoryResponseArray =    webClientBuilder.build()
                              .get()
                              .uri("http://Inventory-Service/api/inventory",
                                  uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                              .retrieve()
                              .bodyToMono(InventoryResponse[].class)
                              .block();


        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(inventoryResponse -> inventoryResponse.isInStock());

          if ( allProductsInStock){
              orderRepository.save(order);
              kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
              return "Order placed successfully ";
          }else{
              throw new IllegalArgumentException("Out of stock, try again later");
          }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
