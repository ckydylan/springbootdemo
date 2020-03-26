package com.cky.springbootdemo.exception;

import com.cky.springbootdemo.common.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: cky
 * @Date: 2020/3/26 08:40
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException{
    protected Integer code;
    protected String message;

    public CustomException(ExceptionType exceptionType){
        this.code =exceptionType.getCode();
        this.message =exceptionType.getMsg();
    }
}