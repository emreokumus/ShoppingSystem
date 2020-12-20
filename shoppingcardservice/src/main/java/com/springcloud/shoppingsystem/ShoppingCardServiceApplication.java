package com.springcloud.shoppingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class ShoppingCardServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingCardServiceApplication.class,args);
    }
}
