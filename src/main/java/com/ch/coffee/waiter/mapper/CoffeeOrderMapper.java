package com.ch.coffee.waiter.mapper;

import com.ch.coffee.waiter.model.CoffeeOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CoffeeOrderMapper {
    CoffeeOrder findById(@Param("id") Long id);
    List<CoffeeOrder> findAll();
    void insertOrder(CoffeeOrder order);
    void insertOrderCoffee(@Param("orderId") Long id, @Param("coffeeIds") List<Long> coffeeIds);
    void update(CoffeeOrder order);
    void delete(@Param("id") Long id);
}
