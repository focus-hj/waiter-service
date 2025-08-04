package com.ch.coffee.waiter.mapper;

import com.ch.coffee.waiter.model.Coffee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CoffeeMapper {
    int saveCoffee(Coffee coffee);
    Coffee getCoffeeById(@Param("id") Long id);
    List<Coffee> getAllCoffees();
    Coffee getCoffeeByName(@Param("name") String name);
    List<Coffee> getCoffeesOrderById(@Param("names") List<String> names);
    long getCoffeeCount();
}
