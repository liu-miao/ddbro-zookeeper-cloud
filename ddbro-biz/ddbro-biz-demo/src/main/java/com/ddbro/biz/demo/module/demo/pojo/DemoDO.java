package com.ddbro.biz.demo.module.demo.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DemoDO {
    private Long id;
    private String name;
    private Integer deleted;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
