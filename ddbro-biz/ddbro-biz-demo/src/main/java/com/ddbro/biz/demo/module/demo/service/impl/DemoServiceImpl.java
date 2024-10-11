package com.ddbro.biz.demo.module.demo.service.impl;

import com.ddbro.biz.demo.module.demo.entity.Demo;
import com.ddbro.biz.demo.module.demo.mapper.DemoMapper;
import com.ddbro.biz.demo.module.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoMapper demoMapper;

    @Override
    public Demo findByName(String loginName) {
        Demo demo = demoMapper.findByName(loginName);
        return demo;
    }

}
