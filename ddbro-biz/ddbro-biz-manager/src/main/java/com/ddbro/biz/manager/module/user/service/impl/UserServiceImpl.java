package com.ddbro.biz.manager.module.user.service.impl;

import com.ddbro.biz.manager.module.user.entity.User;
import com.ddbro.biz.manager.module.user.mapper.UserMapper;
import com.ddbro.biz.manager.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getbyId(Long id) {
        return userMapper.selectById(id);
    }
}
