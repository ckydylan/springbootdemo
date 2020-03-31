package com.cky.springbootdemo.controller;

import com.cky.springbootdemo.common.ExceptionType;
import com.cky.springbootdemo.entity.Message;
import com.cky.springbootdemo.exception.CustomException;
import com.cky.springbootdemo.service.MessageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cky
 * @Date: 2020/3/24 11:24
 * @Description:
 */
@RestController
@Api(tags = {"消息 API"}, produces = "http")
@SwaggerDefinition(tags = {
        @Tag(name = "消息 API", description = "消息管理的API的接口")
})
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    @ApiOperation(value = "消息列表",
            notes = "完整的消息内容列表",
            produces = "application/json,application/xml",
            consumes = "application/json,application/xml")
    public ResponseEntity<List<Message>> list() {
        List<Message> list = this.messageService.findAll();
        if (!list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/message")
    @ApiOperation(value = "添加消息", notes = "根据参数创建消息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "消息 ID", required = true, dataType = "Long", paramType = "query"),
//            @ApiImplicitParam(name = "text", value = "正文", required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "summary", value = "摘要", dataType = "String", paramType = "query")})
    public ResponseEntity<Message> create(@RequestBody Message message) {
        Message msg = this.messageService.save(message);
        return ResponseEntity.ok(msg);
    }

    @PutMapping("/message")
    @ApiOperation(value = "修改消息", notes = "根据参数修改消息")
    public ResponseEntity<Message> modify(@RequestBody Message message) {
        Message msg = this.messageService.update(message);
        return ResponseEntity.ok(msg);
    }

    @PatchMapping("/message/text")
    @ApiOperation(value = "修改消息内容", notes = "根据参数修改消息内容")
    public ResponseEntity<Message> path(@RequestBody Message message) {
        if (message == null || message.getText() == null || message.getText().isEmpty()) {
            throw new CustomException(ExceptionType.USER_INPUT_ERROR);
        }
        try {
            Message msg = this.messageService.updateText(message);
            return ResponseEntity.ok(msg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ExceptionType.SERVER_ERROR.getCode(), e.getMessage());
        }

    }

    @GetMapping("/message/{id}")
    @ApiOperation(value = "获取消息", notes = "根据id获取消息")
    public ResponseEntity<Message> get(@PathVariable Long id) {
        Message msg = this.messageService.findOne(id);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/message/{id}")
    @ApiOperation(value = "删除消息", notes = "根据参数删除消息")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        this.messageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exception")
    public String exception() {
//        int i = 1 / 0;
        return "exception";
    }

    @GetMapping("/userException")
    public String userException() {
        throw new CustomException(ExceptionType.USER_INPUT_ERROR);
    }
}