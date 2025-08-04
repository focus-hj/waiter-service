package com.ch.coffee.waiter;

import com.ch.coffee.waiter.integration.Barista;
import com.ch.coffee.waiter.integration.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
@EnableBinding({Barista.class, Customer.class})
public class WaiterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WaiterServiceApplication.class, args);
    }
}
