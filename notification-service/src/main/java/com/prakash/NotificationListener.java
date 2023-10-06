package com.prakash;


import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(topics = "notificationTopic", groupId = "notificationId")
public class NotificationListener {
    @KafkaHandler
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received Notification for order - {}", orderPlacedEvent.getOrderNumber());
    }
}
