package com.company.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.project.entity.InterfaceBodyEntity;
import com.company.project.entity.InterfaceHeaderEntity;

public interface InterfaceBodyMapper extends BaseMapper<InterfaceBodyEntity> {
    Integer insertNotEx(InterfaceBodyEntity interfaceBodyEntity);
}
