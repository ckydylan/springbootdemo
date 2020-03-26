package com.cky.springbootdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: cky
 * @Date: 2020/3/24 11:22
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Long id;
    private String text;
    private String summary;
}