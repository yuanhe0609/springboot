package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("interface_code")
    private String interfaceCode;
    @TableField("creator_name")
    private String interfaceCreator;
    @TableField("updater_name")
    private String interfaceUpdater;
    @TableField("create_time")
    private Date interfaceCreateTime;
    @TableField("update_time")
    private Date interfaceUpdateTime;
}
