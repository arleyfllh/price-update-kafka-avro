package com.price_update_kafka_avro.consumer.service;

import com.price_update_kafka_avro.avro_models.PriceUpdate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${kafka.topic}", groupId = "price-update-group")
    public void listen(PriceUpdate priceUpdate) {
        System.out.println("Received price update: " + priceUpdate);
    }

}
