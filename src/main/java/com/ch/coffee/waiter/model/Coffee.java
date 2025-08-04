package com.ch.coffee.waiter.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Coffee {
    private Long id;
    private String name;
    private Integer price;
    private Date createTime;
    private Date updateTime;
}
