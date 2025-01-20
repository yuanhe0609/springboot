package com.company.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.project.entity.InterfaceBodyEntity;

public interface InterfaceBodyMapper extends BaseMapper<InterfaceBodyEntity> {
    /**
     * @description 插入目前没有的参数
     * @param interfaceBodyEntity 请求正文实体
     * @return 返回插入数据数量
     * */
    Integer insertNotEx(InterfaceBodyEntity interfaceBodyEntity);
}
