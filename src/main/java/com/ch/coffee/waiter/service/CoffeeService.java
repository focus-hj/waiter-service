package com.ch.coffee.waiter.service;

import com.ch.coffee.waiter.model.Coffee;
import com.ch.coffee.waiter.repository.CoffeeDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CoffeeService {
    @Autowired
    private CoffeeDao coffeeDao;

    public Coffee saveCoffee(String name, Integer price) {
        long currentTime = System.currentTimeMillis();
        Coffee coffee = Coffee.builder()
                .name(name)
                .price(price)
                .createTime(new Date(currentTime))
                .updateTime(new Date(currentTime))
                .build();
        return coffeeDao.saveCoffee(coffee);
    }

    public List<Coffee> getAllCoffee() {
        return coffeeDao.getAllCoffees();
    }

    public Coffee getCoffee(Long id) {
        return coffeeDao.getCoffeeById(id);
    }

    public Coffee getCoffee(String name) {
        return coffeeDao.getCoffeeByName(name);
    }

    public long getCoffeeCount() {
        return coffeeDao.getCoffeeCount();
    }

    public List<Coffee> getCoffeeByName(List<String> names) {
        return coffeeDao.getCoffeesOrderById(names);
    }
}
