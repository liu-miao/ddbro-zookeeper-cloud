package com.ddbro.biz.demo.module.demo.controller;


import com.ddbro.biz.demo.module.demo.entity.Demo;
import com.ddbro.biz.demo.module.demo.service.DemoService;
import com.ddbro.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("user")
    public R<Demo> user(@RequestParam("name") String name) {
        Demo demo = demoService.findByName(name);
        return R.success(demo);
    }

    @GetMapping("say")
    public R<String> user() {

        return R.success("hi");
    }

}