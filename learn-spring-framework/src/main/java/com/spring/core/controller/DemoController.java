package com.spring.core.controller;

import com.spring.core.service.DemoService;
import org.springframework.stereotype.Controller;

@Controller
public class DemoController {

    private DemoService demoService;

    public String hello(){
        return "hello controller";
    }
}
