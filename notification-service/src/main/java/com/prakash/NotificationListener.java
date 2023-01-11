package com.prakash;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
@Slf4j
public class NotificationListener {


    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent){
log.info("Received Notification for order - {}",orderPlacedEvent.getOrderNumber());

    }

}
