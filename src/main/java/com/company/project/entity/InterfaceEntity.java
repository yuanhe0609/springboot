package com.company.project.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_interface")
public class InterfaceEntity extends BaseEntity{
    @TableField("interface_name")
    private String interfaceName;
    @TableField("interface_type")
    private String interfaceType;
    @TableField("interface_protocol")
    private String interfaceProtocol;
    @TableField("interface_host")
    private String interfaceHost;
    @TableField("interface_port")
    private String interfacePort;
    @TableField("interface_address")
    private String interfaceAddress;
    @TableField("interface_status")
    private String interfaceStatus;
    @TableField("interface_description")
    private String interfaceDescription;


}
