package com.vlborkunov.testkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.vlborkunov.testkafka.database.model.Product;
import com.vlborkunov.testkafka.service.ProductService;

@Component
@EnableKafka
public class ProductConsumer {
    @Autowired
    private ProductService service;

    @KafkaListener(topics = "product")
    public void getProductFromKafka(ConsumerRecord<Long, Product> record) {
        System.out.println(record.key() + " " + record.value() + " Ура!");
        System.out.println(record.partition() + " партиция, " + record.topic() + " топик");
        service.saveToDatabase(record.value());
    }
}
