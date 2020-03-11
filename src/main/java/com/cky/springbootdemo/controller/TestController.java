package com.cky.springbootdemo.controller;

import com.cky.springbootdemo.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cky
 * @Date: 2020/3/11 08:56
 * @Description:
 */
@Slf4j
@RestController
public class TestController {
    @Autowired
    private Book book;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/book")
    public String getBook(){
        log.debug("asd");
        return book.toString();
    }
}