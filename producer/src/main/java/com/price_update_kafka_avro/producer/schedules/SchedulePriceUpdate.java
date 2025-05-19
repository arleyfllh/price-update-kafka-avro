package com.price_update_kafka_avro.producer.schedules;

import com.price_update_kafka_avro.avro_models.PriceUpdate;
import com.price_update_kafka_avro.producer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulePriceUpdate {

    @Autowired
    private final KafkaProducerService producer;

    public SchedulePriceUpdate(KafkaProducerService producer) {
        this.producer = producer;
    }

    @Scheduled(fixedRate = 10000)
    public void sendPriceUpdate() {
        String randomString = String.valueOf((int) (Math.random() * 101));
        PriceUpdate object = PriceUpdate.newBuilder()
                .setItemCode(randomString)
                .setItemName("Item" + "#" + randomString)
                .setNewPrice(100000).build();
        producer.sendPriceUpdate(object);
    }
}
