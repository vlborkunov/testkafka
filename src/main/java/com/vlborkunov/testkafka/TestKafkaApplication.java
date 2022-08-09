package com.vlborkunov.testkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TestKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestKafkaApplication.class, args);
    }

}
