package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_interface_body")
public class InterfaceBodyEntity extends BaseEntity{
    /**
     * 参数
     * */
    @TableField("param")
    private String param;
    /**
     * 参数类型
     * */
    @TableField("type")
    private String type;
    /**
     * 默认值
     * */
    @TableField("default_value")
    private String defaultValue;
    /**
     * 参数外层
     * */
    @TableField("parents_param")
    private String parentsParam;
    /**
     * 外层类型
     * */
    @TableField("parents_type")
    private String parentsType;
}
