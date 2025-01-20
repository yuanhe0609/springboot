package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_call_interface_log")
public class CallInterfaceEntity extends BaseEntity{
    /**
     * 请求正文
     * */
    @TableField("data")
    private Object data;
    /**
     * 请求头
     * */
    @TableField("header")
    private Object header;
    /**
     * 相应正文
     * */
    @TableField("result")
    private Object result;
    /**
     * 请求方法
     * */
    @TableField("method")
    private String method;
    /**
     * 消耗时间
     * */
    @TableField("consume_time")
    private Float consumeTime;
    /**
     * 请求状态
     * */
    @TableField("status")
    private String Status;

}
