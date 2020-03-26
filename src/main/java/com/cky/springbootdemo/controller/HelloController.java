package com.cky.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Map;

/**
 * @Author: cky
 * @Date: 2020/3/24 08:38
 * @Description:
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","ccccc");
        return "hello";
    }

    @GetMapping("/welcome")
    public String welcome(Map<String,Object> model){
        model.put("name","ccccc");
        model.put("time", LocalDate.now());
        return "welcome";
    }
}