package com.company.project.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.project.entity.CallInterfaceEntity;
import com.company.project.entity.InterfaceEntity;
import com.company.project.mapper.CallInterfaceMapper;
import com.company.project.mapper.InterfaceMapper;
import com.company.project.service.CallInterfaceService;
import com.company.project.utils.CallInterfaceUtil;
import org.apache.commons.httpclient.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class CallInterFaceServiceImpl implements CallInterfaceService {
    @Autowired
    InterfaceMapper interfaceMapper;
    @Autowired
    CallInterfaceMapper callInterfaceMapper;
    /**
     * @description 调用外部接口
     * @param interfaceCode 接口编码
     * @param header 请求头
     * @param body 请求正文
     * @return 返回接口返回值
     * */
    @Override
    public JSONObject CallInterface(String interfaceCode, JSONObject header, JSONObject body) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        Logger logger = Logger.getLogger("CallInterfaceLog");
        QueryWrapper<InterfaceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interface_code", interfaceCode);

        InterfaceEntity interfaceEntity = interfaceMapper.selectOne(queryWrapper);
        JSONObject result = new JSONObject();
        if("POST".equals(interfaceEntity.getInterfaceType())){
            CallInterfaceUtil callInterfaceUtil = new CallInterfaceUtil();
            result = callInterfaceUtil.PostAction(interfaceEntity,header,body,logger);
        }else if("GET".equals(interfaceEntity.getInterfaceType())){
            CallInterfaceUtil callInterfaceUtil = new CallInterfaceUtil();
            result = callInterfaceUtil.GetAction(interfaceEntity,header,body,logger);
        }
        saveResponse(interfaceEntity,header,body,result);
        return result;
    }
    public void saveResponse(InterfaceEntity interfaceEntity,JSONObject header, JSONObject body,Map result){
        CallInterfaceEntity callInterfaceEntity = new CallInterfaceEntity();
        callInterfaceEntity.setId(null);
        callInterfaceEntity.setInterfaceCode(interfaceEntity.getInterfaceCode());
        callInterfaceEntity.setHeader(header.toString());
        callInterfaceEntity.setData(body.toString());
        callInterfaceEntity.setConsumeTime(Float.parseFloat(result.get("timeElapsed").toString())/1000);
        callInterfaceEntity.setResult(result.toString());
        callInterfaceEntity.setMethod(interfaceEntity.getInterfaceType());
        callInterfaceEntity.setStatus(result.get("code").toString());
        callInterfaceMapper.insert(callInterfaceEntity);
    }
}
