package com.ch.coffee.waiter.service;

import com.ch.coffee.waiter.integration.Barista;
import com.ch.coffee.waiter.model.Coffee;
import com.ch.coffee.waiter.model.CoffeeOrder;
import com.ch.coffee.waiter.model.OrderState;
import com.ch.coffee.waiter.repository.CoffeeOrderDao;
import com.ch.coffee.waiter.support.OrderProperties;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Transactional
@Service
@Slf4j
public class CoffeeOrderService implements MeterBinder {
    @Autowired
    private OrderProperties orderProperties;
    @Autowired
    private CoffeeOrderDao coffeeOrderDao;
    @Autowired
    private Barista barista;

    private String waiterId = UUID.randomUUID().toString();

    private Counter orderCounter;

    public CoffeeOrder get(Long id) {
        return coffeeOrderDao.findById(id);
    }

    public CoffeeOrder createOrder(String customer, Coffee...coffee) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffee))
                .discount(orderProperties.getDiscount())
                .total(calcTotal(coffee))
                .waiter(orderProperties.getWaiterPrefix() + waiterId)
                .build();
        CoffeeOrder saved = coffeeOrderDao.save(order);
        log.info("New Order: {}", saved);
        orderCounter.increment();
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (order == null) {
            log.warn("Can not find order.");
            return false;
        }

        if (state.compareTo(order.getState()) < 0) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }

        order.setState(state);
        coffeeOrderDao.update(order);
        log.info("Order updated: {}", order);
        if (state == OrderState.PAID) {
            barista.newOrders().send(MessageBuilder.withPayload(order.getId()).build());
        }
        return true;
    }

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        this.orderCounter = meterRegistry.counter("order.count");
    }

    private Integer calcTotal(Coffee...coffee) {
        AtomicInteger total = new AtomicInteger();
        Stream.of(coffee).forEach(c -> total.addAndGet(c.getPrice()));
        return total.get() * orderProperties.getDiscount();
    }
}
