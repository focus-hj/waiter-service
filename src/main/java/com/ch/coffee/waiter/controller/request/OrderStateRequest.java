package com.ch.coffee.waiter.controller.request;

import com.ch.coffee.waiter.model.OrderState;
import lombok.Data;

@Data
public class OrderStateRequest {
    private OrderState state;
}
