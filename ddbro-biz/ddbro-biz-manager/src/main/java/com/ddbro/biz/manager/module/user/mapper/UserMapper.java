package com.ddbro.biz.manager.module.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddbro.biz.manager.module.user.entity.User;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    default User findByUsername(String username) {
        return selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }
}
