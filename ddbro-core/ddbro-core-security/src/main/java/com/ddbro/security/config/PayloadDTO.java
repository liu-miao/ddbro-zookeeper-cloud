package com.ddbro.security.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


/**
 * 有效载荷dto
 *
 * @author miaol
 * @date 2020-09-22 11:27
 */
@Getter
@Setter
public class PayloadDTO {


    /**
     * 主题
     */
    @Value("${spring.application.name}")
    private String sub;

    /**
     * 签发时间
     */
    private Long iat = System.currentTimeMillis();

    /**
     * 用户ID
     */
    @NotBlank
    private String userId;

    /**
     * 用户名
     */
    @NotBlank
    private String userName;


    /**
     * 用户所在工作空间id
     */
    @NotNull
    private Long workspaceId;

}
