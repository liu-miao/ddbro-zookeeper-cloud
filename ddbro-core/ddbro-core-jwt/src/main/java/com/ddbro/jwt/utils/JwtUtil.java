package com.ddbro.jwt.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.ddbro.core.exception.Assert;
import com.ddbro.jwt.PayloadDTO;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * JWT工具类
 */
@Slf4j
public class JwtUtil {

    @Value("jwt.secret_key")
    public static String JWT_SECRET_KEY;

    public static String generateToken(String userId, String username, Collection<String> authorities) {
        Assert.isFalse(CharSequenceUtil.isBlank(username)
                || CharSequenceUtil.isBlank(userId), "获取token失败，账户信息异常");
        PayloadDTO payloadDTO = new PayloadDTO();
        payloadDTO.setUserId(userId);
        payloadDTO.setUserName(username);
        if (CollUtil.isNotEmpty(authorities)) {
            payloadDTO.setAuthorities(authorities);
        }
        String token = null;
        try {
            //创建JWS头，设置签名算法和类型
            JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).
                    type(JOSEObjectType.JWT)
                    .build();
            //将负载信息封装到Payload中
            Payload payload = new Payload(JSONUtil.toJsonStr(payloadDTO));
            //创建JWS对象
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);
            //创建HMAC签名器
            JWSSigner jwsSigner = new MACSigner(SecureUtil.md5(JWT_SECRET_KEY));
            //签名
            jwsObject.sign(jwsSigner);
            token = jwsObject.serialize();
        } catch (Exception e) {
            log.error(ExceptionUtil.stacktraceToString(e));
            Assert.isError("token获取失败,请稍后重试！");
        }
        return token;
    }

    /**
     * 解析token
     *
     * @param token 令牌
     * @return {@link PayloadDTO}
     */
    public static PayloadDTO analysisToken(String token) {
        try {
            if (CharSequenceUtil.isBlank(token)) {
                return null;
            }
            //从token中解析JWS对象
            JWSObject jwsObject = JWSObject.parse(token);
            //创建HMAC验证器
            JWSVerifier jwsVerifier = new MACVerifier(SecureUtil.md5(JWT_SECRET_KEY));
            boolean verify = jwsObject.verify(jwsVerifier);
            if (!verify) {
                return null;
            }
            String payload = jwsObject.getPayload().toString();
            return JSONUtil.toBean(payload, PayloadDTO.class);
        } catch (Exception e) {
            log.error(ExceptionUtil.stacktraceToString(e));
            return null;
        }

    }
}