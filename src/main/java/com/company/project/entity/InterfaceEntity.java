package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_interface")
public class InterfaceEntity extends BaseEntity{
    /**
     * 接口名称
     * */
    @TableField("interface_name")
    private String interfaceName;
    /**
     * 请求方式
     * */
    @TableField("interface_type")
    private String interfaceType;
    /**
     * 接口协议
     * */
    @TableField("interface_protocol")
    private String interfaceProtocol;
    /**
     * 接口主机
     * */
    @TableField("interface_host")
    private String interfaceHost;
    /**
     * 接口端口
     * */
    @TableField("interface_port")
    private String interfacePort;
    /**
     * 接口地址
     * */
    @TableField("interface_address")
    private String interfaceAddress;
    /**
     * 接口状态
     * */
    @TableField("interface_status")
    private String interfaceStatus;
    /**
     * 接口描述
     * */
    @TableField("interface_description")
    private String interfaceDescription;


}
