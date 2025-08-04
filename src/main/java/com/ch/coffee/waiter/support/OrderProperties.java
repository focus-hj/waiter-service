package com.ch.coffee.waiter.support;

import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("order")
@Reference
@Data
@Component
public class OrderProperties {
    private Integer discount = 100;
    private String waiterPrefix = "ch-coffee-";
}
