package com.ddbro.security.config;

import cn.hutool.core.text.CharSequenceUtil;
import com.ddbro.core.constant.GlobalConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

//    static String eMsg  = "认证信息已失效,请重新登录！";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 白名单
        if (check(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        //获取请求头中的token
        String token = getToken(request);
        if (CharSequenceUtil.isBlank(token) || token.startsWith("Basic ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // token 解析
//        PayloadDTO payloadDTO = JwtUtil.analysisToken(token);
//        if (null == payloadDTO) {
////           WebUtils.renderString(HttpStatus.UNAUTHORIZED.value(),eMsg);
//            return;
//        }
//
//        // 如果是  没有进入工作空间 只能操作部分  接口
//        if (Objects.equals(Constants.INIT_WORKSPACE, payloadDTO.getWorkspaceId())) {
//            //
//            if (!unInWorkspace(request.getRequestURI())) {
////                WebUtils.renderString(HttpStatus.UNAUTHORIZED.value(),eMsg);
//                return;
//            }
//        }


        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(GlobalConstant.AUTHORIZATION_TOKEN);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (CharSequenceUtil.isBlank(token)) {
            return null;
        }

        if (token.length() < 100) {
            return null;
        }
        if (token.startsWith(GlobalConstant.AUTHORIZATION_TOKEN_BEARER.toLowerCase().trim())) {
            token = token.replaceFirst(GlobalConstant.AUTHORIZATION_TOKEN_BEARER.toLowerCase(), "");
        }
        if (token.startsWith(GlobalConstant.AUTHORIZATION_TOKEN_BEARER.trim())) {
            token = token.replaceFirst(GlobalConstant.AUTHORIZATION_TOKEN_BEARER, "");
        }
        return token;
    }

    private boolean check(String requestURI) {
        List<String> whitelist = Arrays.asList("/auth/getToken", "/auth/login", "/file/download");

        // 检查白名单
        boolean isAllowed = false;
        for (String url : whitelist) {
            if (requestURI.startsWith(url)) {
                isAllowed = true;
                break;
            }
        }
        return isAllowed;
    }

    /**
     * 未进入工作空间时 白名单
     *
     * @param requestURI 请求uri
     * @return boolean
     */
    private boolean unInWorkspace(String requestURI) {
        List<String> whitelist = Arrays.asList("/auth/login",
                "/auth/logout", "/workspace/queryWorkspaces", "/user/getCurrentUserInfo", "/datatype/findAllNamespace",
                "/user/switchUserWorkspace", "/workspace/addWorkspace", "/workspace/createByTemplate");

        // 检查白名单
        boolean isAllowed = false;
        for (String url : whitelist) {
            if (requestURI.startsWith(url)) {
                isAllowed = true;
                break;
            }
        }
        return isAllowed;

    }
}
