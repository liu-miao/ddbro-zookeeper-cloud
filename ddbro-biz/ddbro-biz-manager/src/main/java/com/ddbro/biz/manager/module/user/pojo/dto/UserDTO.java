package com.ddbro.biz.manager.module.user.pojo.dto;

import com.ddbro.dataCore.entity.BaseAuditable;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO extends BaseAuditable {
    private Long id;
    private String name;
    private Integer deleted;
    private LocalDateTime gmtModified;
    private LocalDateTime gmtCreate;
}
