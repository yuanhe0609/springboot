package com.company.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.project.entity.InterfaceHeaderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InterfaceHeaderMapper extends BaseMapper<InterfaceHeaderEntity> {
    /**
     * @description 插入目前没有的参数
     * @param interfaceHeaderEntity 请求头实体
     * @return 返回插入数据数量
     * */
    Integer insertNotEx(InterfaceHeaderEntity interfaceHeaderEntity);
}
