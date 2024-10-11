package com.ddbro.biz.manager.module.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ddbro.dataCore.entity.BaseAuditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户表
 */
@TableName("user")
@Data
public class User extends BaseAuditable {
    @TableId(type = IdType.INPUT)
    private String id;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "是否删除，0未删除:1已删除（默认未删除）")
    private Integer deleted = 0;
    @JsonIgnore
    @Schema(description = "密码")
    private String password;
    @JsonIgnore
    @Schema(description = "盐")
    private String salt;
}
