package com.ddbro.biz.manager.module.login.controller;


import com.ddbro.biz.manager.module.login.service.LoginService;
import com.ddbro.biz.manager.module.login.vo.LoginVO;
import com.ddbro.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping()
    public R<String> user(@RequestBody LoginVO loginVO) {
        String login = loginService.login(loginVO.getUsername(), loginVO.getPassword());
        return R.success(login);
    }

}