package com.example.orderservice4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderService4Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderService4Application.class, args);
    }

}
