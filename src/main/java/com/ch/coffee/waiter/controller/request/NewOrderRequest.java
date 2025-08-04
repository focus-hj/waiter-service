package com.ch.coffee.waiter.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class NewOrderRequest {
    private String customer;
    private List<String> items;
}
