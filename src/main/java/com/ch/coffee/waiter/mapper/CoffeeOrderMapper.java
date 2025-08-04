package com.ch.coffee.waiter.mapper;

import com.ch.coffee.waiter.model.CoffeeOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CoffeeOrderMapper {
    CoffeeOrder findById(@Param("id") Long id);
    List<CoffeeOrder> findAll();
    CoffeeOrder save(CoffeeOrder order);
    void update(CoffeeOrder order);
    void delete(@Param("id") Long id);
}
