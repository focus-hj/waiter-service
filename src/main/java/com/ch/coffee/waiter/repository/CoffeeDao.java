package com.ch.coffee.waiter.repository;

import com.ch.coffee.waiter.mapper.CoffeeMapper;
import com.ch.coffee.waiter.model.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoffeeDao {
    @Autowired
    private CoffeeMapper coffeeMapper;

    public Coffee saveCoffee(Coffee coffee) {
        int i = coffeeMapper.saveCoffee(coffee);
        if (i <= 0) {
            throw new RuntimeException("Coffee save failed, coffee: " + coffee);
        }
        return coffee;
    }


    public Coffee getCoffeeById(Long id) {
        return coffeeMapper.getCoffeeById(id);
    }

    public List<Coffee> getAllCoffees() {
        return coffeeMapper.getAllCoffees();
    }

    public Coffee getCoffeeByName(String name) {
        return coffeeMapper.getCoffeeByName(name);
    }

    public List<Coffee> getCoffeesOrderById(List<String> names) {
        return coffeeMapper.getCoffeesOrderById(names);
    }

    public long getCoffeeCount() {
        return coffeeMapper.getCoffeeCount();
    }
}
