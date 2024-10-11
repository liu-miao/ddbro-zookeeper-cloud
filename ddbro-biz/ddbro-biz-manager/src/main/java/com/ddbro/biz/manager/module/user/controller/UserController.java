package com.ddbro.biz.manager.module.user.controller;


import com.ddbro.biz.manager.module.user.entity.User;
import com.ddbro.biz.manager.module.user.service.UserService;
import com.ddbro.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public R<User> getById(@RequestParam("id") Long id) {
        User user = userService.getbyId(id);
        return R.success(user);
    }

}