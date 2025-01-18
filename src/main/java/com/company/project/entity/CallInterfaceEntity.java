package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_call_interface_log")
public class CallInterfaceEntity extends BaseEntity{
    @TableField("data")
    private Object data;
    @TableField("header")
    private Object header;
    @TableField("result")
    private Object result;
    @TableField("method")
    private String method;
    @TableField("invoke_time")
    private Date invokeTime;
    @TableField("consume_time")
    private Long consumeTime;
    @TableField("invoker_name")
    private String invokerName;
    @TableField("status")
    private String Status;

}
