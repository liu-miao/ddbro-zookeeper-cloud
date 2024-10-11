package com.ddbro.biz.demo.module.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddbro.biz.demo.module.demo.entity.Demo;

public interface DemoMapper extends BaseMapper<Demo> {
    Demo findByName(String loginName);
}
