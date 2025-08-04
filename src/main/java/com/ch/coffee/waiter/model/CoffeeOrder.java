package com.ch.coffee.waiter.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class CoffeeOrder {
    private Long id;
    private String customer;
    private List<Coffee> items;
    private OrderState state;
    private Integer discount;
    private Integer total;
    private String waiter;
    private String barista;
    private Date createTime;
    private Date updateTime;
}
