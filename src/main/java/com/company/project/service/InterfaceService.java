package com.company.project.service;

import com.alibaba.fastjson2.JSONObject;
import com.company.project.entity.InterfaceEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InterfaceService {
    Integer addNewInterface(InterfaceEntity interfaceEntity);
    List<InterfaceEntity> getAllInterfaceList();
    Integer updateInterface(InterfaceEntity interfaceEntity);
    Integer deleteInterface(String interfaceCode);
    void parseInterfaceHeaderToList(String interfaceCode,JSONObject header);
    void parseInterfaceBodyToList(String interfaceCode,JSONObject body,String parentsParam,String parentsType);
    JSONObject parseInterfaceBodyToJSONObject(String interfaceCode);
}
