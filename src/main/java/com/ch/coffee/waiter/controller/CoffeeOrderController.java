package com.ch.coffee.waiter.controller;

import com.ch.coffee.waiter.controller.request.NewOrderRequest;
import com.ch.coffee.waiter.controller.request.OrderStateRequest;
import com.ch.coffee.waiter.model.Coffee;
import com.ch.coffee.waiter.model.CoffeeOrder;
import com.ch.coffee.waiter.service.CoffeeOrderService;
import com.ch.coffee.waiter.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeOrderService coffeeOrderService;
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/{id}")
    public CoffeeOrder getOrder(@PathVariable long id) {
        CoffeeOrder order = coffeeOrderService.get(id);
        log.info("Get Order: {}", order);
        return order;
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest request) {
        log.info("Request: {}", request);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(request.getItems()).toArray(new Coffee[]{});
        return coffeeOrderService.createOrder(request.getCustomer(), coffeeList);
    }

    @PutMapping("id")
    public CoffeeOrder updateState(@PathVariable Long id, @RequestBody OrderStateRequest orderState) {
        log.info("Update order {} with state {}", id, orderState);
        CoffeeOrder coffeeOrder = coffeeOrderService.get(id);
        coffeeOrderService.updateState(coffeeOrder, orderState.getState());
        return coffeeOrder;
    }
}
