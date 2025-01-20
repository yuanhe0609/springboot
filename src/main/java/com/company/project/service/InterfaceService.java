package com.company.project.service;

import com.alibaba.fastjson2.JSONObject;
import com.company.project.entity.InterfaceEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InterfaceService {
    /**
     * @description 插入接口信息
     * @param interfaceEntity 接口实体
     * @return 返回插入接口数量
     * */
    Integer addNewInterface(InterfaceEntity interfaceEntity);
    /**
     * @description 查询接口列表
     * @return 返回接口列表
     * */
    List<InterfaceEntity> getAllInterfaceList();
    /**
     * @description 更新接口信息
     * @param interfaceEntity 接口实体
     * @return 返回更新接口数量
     * */
    Integer updateInterface(InterfaceEntity interfaceEntity);
    /**
     * @description 删除接口信息
     * @param interfaceCode 接口编码
     * @return 返回删除接口数量
     * */
    Integer deleteInterface(String interfaceCode);
    /**
     * @description 请求头转参数集合
     * @param interfaceCode 接口编码
     * @param header 请求头
     * */
    void parseInterfaceHeaderToList(String interfaceCode,JSONObject header);
    /**
     * @description 请求正文转参数集合
     * @param interfaceCode 接口编码
     * @param body 请求头
     * @param parentsParam 外层参数名
     * @param parentsType 外层参数类型
     * */
    void parseInterfaceBodyToList(String interfaceCode,JSONObject body,String parentsParam,String parentsType);
    /**
     * @description 参数集合转请求正文格式
     * @param interfaceCode 接口编码
     * @return 请求正文格式
     * */
    JSONObject parseInterfaceBodyToJSONObject(String interfaceCode);
}
