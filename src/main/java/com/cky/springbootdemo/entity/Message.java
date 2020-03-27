package com.cky.springbootdemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "消息对象")
public class Message {
    @ApiModelProperty(notes = "消息ID",name="id",required = true,dataType = "Long",value = "1")
    private Long id;
    @ApiModelProperty(notes = "正文",name="text",required = true,dataType = "String",value = "正文")
    private String text;
    @ApiModelProperty(notes = "摘要",name="summary",required = true,dataType = "String",value = "摘要")
    private String summary;
}