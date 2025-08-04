package com.ch.coffee.waiter.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Barista {
    String NEW_ORDERS = "newOrders";
    String FINISH_ORDERS = "finishOrders";

    @Input
    SubscribableChannel finishOrders();

    @Output
    MessageChannel newOrders();
}
