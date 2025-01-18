package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_interface_body")
public class InterfaceBodyEntity extends BaseEntity{
    @TableField("param")
    private String param;
    @TableField("type")
    private String type;
    @TableField("default_value")
    private String defaultValue;
    @TableField("parents_param")
    private String parentsParam;
    @TableField("parents_type")
    private String parentsType;
}
