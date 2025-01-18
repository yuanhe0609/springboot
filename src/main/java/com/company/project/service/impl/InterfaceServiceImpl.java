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

    @Override
    public Integer addNewInterface(InterfaceEntity interfaceEntity) {
        Integer row = interfaceMapper.insert(interfaceEntity);
        return row;
    }

    @Override
    public List<InterfaceEntity> getAllInterfaceList() {
        List<InterfaceEntity> interfaceEntityList = interfaceMapper.selectList(null);
        return interfaceEntityList;
    }

    @Override
    public Integer updateInterface(InterfaceEntity interfaceEntity) {
        UpdateWrapper<InterfaceEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interface_code", interfaceEntity.getInterfaceCode());
        return interfaceMapper.update(interfaceEntity, updateWrapper);
    }

    @Override
    public Integer deleteInterface(String interfaceCode) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interface_code", interfaceCode);
        return interfaceMapper.delete(queryWrapper);
    }

    @Override
    public void parseInterfaceHeaderToList(String interfaceCode,JSONObject header) {
        Set headerKeySet = header.keySet();
        for(Object key : headerKeySet){
            InterfaceHeaderEntity interfaceHeaderEntity = new InterfaceHeaderEntity();
            interfaceHeaderEntity.setInterfaceCode(interfaceCode);
            interfaceHeaderEntity.setParam(key.toString());
            interfaceHeaderEntity.setDefaultValue(null);
            interfaceHeaderEntity.setInterfaceCreator("管理员");
            interfaceHeaderEntity.setInterfaceCreateTime(new Date());
            interfaceHeaderEntity.setInterfaceUpdater("管理员");
            interfaceHeaderEntity.setInterfaceUpdateTime(new Date());
            interfaceHeaderMapper.insertNotEx(interfaceHeaderEntity);
        }
    }

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
                interfaceBodyEntity.setParentsParam("null");
            }else{
                interfaceBodyEntity.setParentsParam(parentsParam);
            }
            interfaceBodyEntity.setParentsType(parentsType);
            interfaceBodyEntity.setInterfaceCreator("管理员");
            interfaceBodyEntity.setInterfaceCreateTime(new Date());
            interfaceBodyEntity.setInterfaceUpdater("管理员");
            interfaceBodyEntity.setInterfaceUpdateTime(new Date());
            System.out.println(interfaceBodyEntity);
            interfaceBodyMapper.insertNotEx(interfaceBodyEntity);
        }
    }
    @Override
    public JSONObject parseInterfaceBodyToJSONObject(String interfaceCode) {
        JSONObject body = new JSONObject();
        QueryWrapper<InterfaceBodyEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(interfaceCode != null, "interface_code", interfaceCode).orderByAsc("parents_param");
        List<InterfaceBodyEntity> interfaceBodyEntityList = interfaceBodyMapper.selectList(queryWrapper);
        Map bodyMap = new HashMap();
        Map<String, List<InterfaceBodyEntity>> groupByParentsParam = interfaceBodyEntityList.stream().collect(Collectors.groupingBy(InterfaceBodyEntity::getParentsParam));
        Set keySet = groupByParentsParam.keySet();
        JSONObject jsonObject = new JSONObject();
        for(Object key : keySet){
            if("null".equals(key.toString())){
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
                        tmpObject.put(interfaceBodyEntity.getParam(),"");
                        jsonObject.put(key.toString(),tmpObject);
                    }
                }
                if(jsonObject.get(key.toString()).getClass() == JSONArray.class){
                    jsonObject.put(key.toString(),tmpArray);
                }
            }
        }
        System.out.println(jsonObject);
        return body;
    }

}
