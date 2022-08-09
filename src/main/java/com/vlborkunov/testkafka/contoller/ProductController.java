package com.vlborkunov.testkafka.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.vlborkunov.testkafka.database.model.Product;
import com.vlborkunov.testkafka.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> create(final @RequestBody Product product) {
        service.sendToKafka(product);

        final ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        return ResponseEntity.created(builder.path("/{id}").buildAndExpand(product.getId()).toUri()).body(product);
    }

    @GetMapping
    public List<Product> all() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product get(
            final @PathVariable Long id
    ) {
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(
            final @PathVariable Long id
    ) {
        service.delete(id);
    }

}
