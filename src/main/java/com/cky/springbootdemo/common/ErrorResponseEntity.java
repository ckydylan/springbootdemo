package com.cky.springbootdemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: cky
 * @Date: 2020/3/26 08:36
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseEntity {
    private int code;
    private String message;
}