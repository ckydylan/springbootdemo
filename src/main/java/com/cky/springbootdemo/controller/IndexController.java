package com.cky.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: cky
 * @Date: 2020/3/24 10:30
 * @Description:
 */

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}