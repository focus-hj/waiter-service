package com.ch.coffee.waiter.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NewCoffeeRequest {
    @NotEmpty
    private String name;
    @NotNull
    private Integer price;
}
