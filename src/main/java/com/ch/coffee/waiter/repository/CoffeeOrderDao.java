package com.ch.coffee.waiter.repository;

import com.ch.coffee.waiter.mapper.CoffeeOrderMapper;
import com.ch.coffee.waiter.model.CoffeeOrder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CoffeeOrderDao {
    @Resource
    private CoffeeOrderMapper coffeeOrderMapper;

     public CoffeeOrder findById(Long id) {
        return coffeeOrderMapper.findById(id);
     }

     public CoffeeOrder save(CoffeeOrder order) {
         int i = coffeeOrderMapper.save(order);
         if (i <= 0) {
             throw new RuntimeException("Coffee Order save failed, order: " + order);
         }

         return order;
     }

     public void update(CoffeeOrder order) {
        coffeeOrderMapper.update(order);
     }

     public void delete(Long id) {
        coffeeOrderMapper.delete(id);
     }
}
