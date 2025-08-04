package com.ch.coffee.waiter.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder
public class BaseEntity implements Serializable {
    private Long id;
    private Date createTime;
    private Date updateTime;
}
