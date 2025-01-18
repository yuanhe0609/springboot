package com.company.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.project.entity.InterfaceHeaderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterfaceHeaderMapper extends BaseMapper<InterfaceHeaderEntity> {
    Integer insertNotEx(InterfaceHeaderEntity interfaceHeaderEntity);
}
