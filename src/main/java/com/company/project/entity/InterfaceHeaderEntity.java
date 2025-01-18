package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_interface_header")
public class InterfaceHeaderEntity extends BaseEntity{
    @TableField("param")
    private String param;
    @TableField("default_value")
    private String defaultValue;
}
