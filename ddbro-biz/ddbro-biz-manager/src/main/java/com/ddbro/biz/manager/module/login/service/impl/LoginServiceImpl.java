package com.ddbro.biz.manager.module.login.service.impl;

import com.ddbro.biz.manager.module.login.service.LoginService;
import com.ddbro.jwt.utils.JwtUtil;
import com.ddbro.security.config.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        LoginUser principal = (LoginUser)authenticate.getPrincipal();
        return JwtUtil.generateToken(principal.getId(), principal.getLoginName(),principal.getAuths());
    }
}
