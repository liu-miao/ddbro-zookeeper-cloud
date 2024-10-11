//package com.ddbro.cloud.gateway.filter;
//
//
//import com.ddbro.jwt.PayloadDTO;
//import com.ddbro.jwt.constants.SecurityConstants;
//import com.ddbro.jwt.constants.TokenConstants;
//import com.ddbro.jwt.utils.JwtTokenUtils;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * 身份验证过滤器
// * 转发token
// * 用户身份信息权限信息
// *
// * @author miaol
// * @date 2022/01/10
// */
//@Component
//public class AuthFilter implements GlobalFilter, Ordered {
//    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpRequest.Builder mutate = request.mutate();
//        String token = getToken(request);
//        if (null!=token&&!token.isBlank()){
//
//            PayloadDTO payloadDTO = JwtTokenUtils.verifyToken(token);
//            exchange.getRequest().mutate().headers(h -> {
//                h.add(SecurityConstants.USER_ID, String.valueOf(payloadDTO.getUserId()));
//                h.add(SecurityConstants.LOGIN_NAME, payloadDTO.getUserName());
//            });
//
//            // 内部请求来源参数清除
//            removeHeader(mutate, SecurityConstants.FROM_SOURCE);
//            return chain.filter(exchange.mutate().request(mutate.build()).build());
//
//        }
//        return chain.filter(exchange);
//    }
//
//
//    private void removeHeader(ServerHttpRequest.Builder mutate, String name) {
//        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
//    }
//
//
//
//
//
//    /**
//     * 获取请求token
//     */
//    private String getToken(ServerHttpRequest request) {
//        String token = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
//        // 如果前端设置了令牌前缀，则裁剪掉前缀
//        if (StringUtils.isEmpty(token)) {
//            return null;
//        }
//        if (token.startsWith(TokenConstants.PREFIX.toLowerCase())) {
//            token = token.replaceFirst(TokenConstants.PREFIX.toLowerCase(), StringUtils.EMPTY);
//        }
//        if (token.startsWith(TokenConstants.PREFIX)) {
//            token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
//        }
//        return token;
//    }
//
//    @Override
//    public int getOrder() {
//        return -200;
//    }
//
//
//}