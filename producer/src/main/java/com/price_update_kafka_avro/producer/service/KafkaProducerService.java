package com.price_update_kafka_avro.producer.service;

import com.price_update_kafka_avro.avro_models.PriceUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);
    private final KafkaTemplate<String, PriceUpdate> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public KafkaProducerService(KafkaTemplate<String, PriceUpdate> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPriceUpdate(PriceUpdate priceUpdate) {
        kafkaTemplate.send(topic, String.valueOf(priceUpdate.getItemCode()), priceUpdate);
        log.info("Sent PriceUpdate: {}", priceUpdate);
    }
}
