package com.company.project.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.company.project.entity.InterfaceBodyEntity;
import com.company.project.entity.InterfaceEntity;
import com.company.project.entity.InterfaceHeaderEntity;
import com.company.project.mapper.InterfaceBodyMapper;
import com.company.project.mapper.InterfaceHeaderMapper;
import com.company.project.mapper.InterfaceMapper;
import com.company.project.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InterfaceServiceImpl implements InterfaceService {
    @Autowired
    private InterfaceMapper interfaceMapper;
    @Autowired
    private InterfaceHeaderMapper interfaceHeaderMapper;
    @Autowired
    private InterfaceBodyMapper interfaceBodyMapper;
    /**
     * @description 插入接口信息
     * @param interfaceEntity 接口实体
     * @return 返回插入接口数量
     * */
    @Override
    public Integer addNewInterface(InterfaceEntity interfaceEntity) {
        Integer row = interfaceMapper.insert(interfaceEntity);
        return row;
    }
    /**
     * @description 查询接口列表
     * @return 返回接口列表
     * */
    @Override
    public List<InterfaceEntity> getAllInterfaceList() {
        List<InterfaceEntity> interfaceEntityList = interfaceMapper.selectList(null);
        return interfaceEntityList;
    }
    /**
     * @description 更新接口信息
     * @param interfaceEntity 接口实体
     * @return 返回更新接口数量
     * */
    @Override
    public Integer updateInterface(InterfaceEntity interfaceEntity) {
        UpdateWrapper<InterfaceEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interface_code", interfaceEntity.getInterfaceCode());
        return interfaceMapper.update(interfaceEntity, updateWrapper);
    }
    /**
     * @description 删除接口信息
     * @param interfaceCode 接口编码
     * @return 返回删除接口数量
     * */
    @Override
    public Integer deleteInterface(String interfaceCode) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interface_code", interfaceCode);
        return interfaceMapper.delete(queryWrapper);
    }
    /**
     * @description 请求头转参数集合
     * @param interfaceCode 接口编码
     * @param header 请求头
     * */
    @Override
    public void parseInterfaceHeaderToList(String interfaceCode,JSONObject header) {
        Set headerKeySet = header.keySet();
        for(Object key : headerKeySet){
            InterfaceHeaderEntity interfaceHeaderEntity = new InterfaceHeaderEntity();
            interfaceHeaderEntity.setInterfaceCode(interfaceCode);
            interfaceHeaderEntity.setParam(key.toString());
            interfaceHeaderEntity.setDefaultValue(null);
            interfaceHeaderEntity.setInterfaceCreator("管理员");
            interfaceHeaderEntity.setInterfaceUpdater("管理员");
            interfaceHeaderMapper.insertNotEx(interfaceHeaderEntity);
        }
    }
    /**
     * @description 请求正文转参数集合
     * @param interfaceCode 接口编码
     * @param body 请求头
     * @param parentsParam 外层参数名
     * @param parentsType 外层参数类型
     * */
    @Override
    public void parseInterfaceBodyToList(String interfaceCode, JSONObject body, String parentsParam,String parentsType) {
        Set bodyKeySet = body.keySet();
        for(Object key : bodyKeySet){
            if("ArrayList".equals(body.get(key).getClass().getSimpleName())){
                JSONArray array = body.getJSONArray(key.toString());
                if(array != null && array.size() > 0){
                    if("LinkedHashMap".equals(array.get(0).getClass().getSimpleName())){
                            parseInterfaceBodyToList(interfaceCode, array.getJSONObject(0),key.toString(),"JSONArray");
                    }
                }
            }
            if("LinkedHashMap".equals(body.get(key).getClass().getSimpleName())){
                parseInterfaceBodyToList(interfaceCode, body.getJSONObject(key.toString()),key.toString(),"JSONObject");
            }
            InterfaceBodyEntity interfaceBodyEntity = new InterfaceBodyEntity();
            interfaceBodyEntity.setInterfaceCode(interfaceCode);
            interfaceBodyEntity.setParam(key.toString());
            interfaceBodyEntity.setDefaultValue(null);

            interfaceBodyEntity.setType(body.get(key).getClass().getSimpleName());
            if("".equals(parentsParam) || parentsParam == null){
                interfaceBodyEntity.setParentsParam("*");
            }else{
                interfaceBodyEntity.setParentsParam(parentsParam);
            }
            interfaceBodyEntity.setParentsType(parentsType);
            interfaceBodyEntity.setInterfaceCreator("管理员");
            interfaceBodyEntity.setInterfaceUpdater("管理员");
            System.out.println(interfaceBodyEntity);
            interfaceBodyMapper.insertNotEx(interfaceBodyEntity);
        }
    }
    /**
     * @description 参数集合转请求正文格式
     * @param interfaceCode 接口编码
     * @return 请求正文格式
     * */
    @Override
    public JSONObject parseInterfaceBodyToJSONObject(String interfaceCode) {
        QueryWrapper<InterfaceBodyEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(interfaceCode != null, "interface_code", interfaceCode).orderByDesc("parents_param");
        List<InterfaceBodyEntity> interfaceBodyEntityList = interfaceBodyMapper.selectList(queryWrapper);
        Map<String, List<InterfaceBodyEntity>> groupByParentsParam = interfaceBodyEntityList.stream().collect(Collectors.groupingBy(InterfaceBodyEntity::getParentsParam));
        //TODO 目前最外层的上层参数用*存储，用Arrays.sort进行排序，必须先遍历最外层。
        Object[] keySetArray=groupByParentsParam.keySet().toArray();
        Arrays.sort(keySetArray);
        JSONObject jsonObject = new JSONObject();
        for(Object key : keySetArray){
            if("*".equals(key.toString())){
                for(InterfaceBodyEntity interfaceBodyEntity : groupByParentsParam.get(key.toString())){
                    if("JSONArray".equals(interfaceBodyEntity.getType())){
                        jsonObject.put(interfaceBodyEntity.getParam(),new JSONArray());
                    }else{
                        jsonObject.put(interfaceBodyEntity.getParam(),"");
                    }
                }
            }else{
                JSONArray tmpArray = new JSONArray();
                JSONObject tmpObject = new JSONObject();
                for(InterfaceBodyEntity interfaceBodyEntity : groupByParentsParam.get(key.toString())){
                    if("JSONArray".equals(interfaceBodyEntity.getParentsType())){
                        JSONObject tmp = new JSONObject();
                        tmp.put(interfaceBodyEntity.getParam(),"");
                        tmpArray.add(tmp);
                    }else{
                        if("JSONArray".equals(interfaceBodyEntity.getType())){
                            tmpObject.put(interfaceBodyEntity.getParam(),new JSONArray());
                        }else{
                            tmpObject.put(interfaceBodyEntity.getParam(),"");
                        }
                        jsonObject.put(key.toString(),tmpObject);
                    }
                }
                if(jsonObject.get(key.toString()).getClass() == JSONArray.class){
                    jsonObject.put(key.toString(),tmpArray);
                }
            }
        }
        System.out.println(jsonObject);
        return jsonObject;
    }

}