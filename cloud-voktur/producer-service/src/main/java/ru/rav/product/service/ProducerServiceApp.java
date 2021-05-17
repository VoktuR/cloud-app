package ru.rav.product.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProducerServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ProducerServiceApp.class, args);
    }
}
