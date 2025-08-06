package com.ch.coffee.waiter.repository;

import com.ch.coffee.waiter.mapper.CoffeeOrderMapper;
import com.ch.coffee.waiter.model.Coffee;
import com.ch.coffee.waiter.model.CoffeeOrder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CoffeeOrderDao {
    @Resource
    private CoffeeOrderMapper coffeeOrderMapper;

     public CoffeeOrder findById(Long id) {
        return coffeeOrderMapper.findById(id);
     }

     public CoffeeOrder save(CoffeeOrder order) {
         coffeeOrderMapper.insertOrder(order);

         List<Long> coffeeIds = order.getItems().stream().map(Coffee::getId).collect(Collectors.toList());
         coffeeOrderMapper.insertOrderCoffee(order.getId(), coffeeIds);

         return order;
     }

     public void update(CoffeeOrder order) {
        coffeeOrderMapper.update(order);
     }

     public void delete(Long id) {
        coffeeOrderMapper.delete(id);
     }
}
