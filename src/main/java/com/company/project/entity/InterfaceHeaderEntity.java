package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_interface_header")
public class InterfaceHeaderEntity extends BaseEntity{
    /**
     * 参数
     * */
    @TableField("param")
    private String param;
    /**
     * 默认值
     * */
    @TableField("default_value")
    private String defaultValue;
}
