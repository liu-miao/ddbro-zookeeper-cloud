package com.ddbro.biz.demo.module.demo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Demo {
    private Long id;
    private String name;
    private Integer deleted;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
