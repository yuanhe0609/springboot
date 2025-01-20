package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    /**
     * 唯一id
     * */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 接口编码
     * */
    @TableField("interface_code")
    private String interfaceCode;
    /**
     * 创建人
     * */
    @TableField("creator_name")
    private String interfaceCreator;
    /**
     * 创建时间
     * */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date interfaceCreateTime;
    /**
     * 更新人
     * */
    @TableField("updater_name")
    private String interfaceUpdater;
    /**
     * 更新时间
     * */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date interfaceUpdateTime;
}
