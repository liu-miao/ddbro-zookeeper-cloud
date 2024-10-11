package com.ddbro.jwt;

import lombok.Data;

@Data
public class AuthToken {

    private String accessToken;

    private String tokeType;

    private String refreshToken;

    private Integer expiresIn;

    private String scope;

    private String openId;
}
