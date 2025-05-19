package com.price_update_kafka_avro.producer.controller;

import com.price_update_kafka_avro.avro_models.PriceUpdate;
import com.price_update_kafka_avro.avro_models.dto.PriceUpdateDto;
import com.price_update_kafka_avro.producer.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductPriceController {
    private final KafkaProducerService service;

    public ProductPriceController(KafkaProducerService service) {
        this.service = service;
    }

    @PostMapping("/price/update")
    public ResponseEntity<String> updatePrice(@RequestBody PriceUpdateDto request) {
        PriceUpdate entity = PriceUpdate.newBuilder()
                .setItemCode(request.getItemCode())
                .setItemName(request.getItemName())
                .setNewPrice(request.getNewPrice()).build();

        service.sendPriceUpdate(entity);
        return ResponseEntity.ok("Price Update Sent");
    }
}
