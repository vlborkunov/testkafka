package com.vlborkunov.testkafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.vlborkunov.testkafka.database.ProductDAO;
import com.vlborkunov.testkafka.database.model.Product;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    KafkaTemplate<Long, Product> kafkaTemplate;

    public void saveToDatabase(Product product) {
        productDAO.insert(product);
    }

    public void sendToKafka(Product product) {
        kafkaTemplate.send("product", product.getId(), product);
    }

    public List<Product> getAll() {
        return productDAO.findAll();
    }

    public Product get(Long id) {
        return productDAO.findById(id).get();
    }

    public void delete(Long id) {
        productDAO.deleteById(id);
    }
}
