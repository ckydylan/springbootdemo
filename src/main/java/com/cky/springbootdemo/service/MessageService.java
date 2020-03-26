package com.cky.springbootdemo.service;

import com.cky.springbootdemo.entity.Message;
import com.cky.springbootdemo.exception.CustomException;

import java.util.List;

public interface MessageService {
    List<Message> findAll();
    Message findOne(Long id);
    Message save(Message message) throws CustomException;
    void delete(Long id);
    Message update(Message message) throws CustomException;
    Message updateText(Message message) throws CustomException;
}
