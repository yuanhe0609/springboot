package com.company.project.utils;

import com.alibaba.fastjson2.JSONObject;
import com.company.project.entity.InterfaceEntity;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class CallInterfaceUtil {
    public Map PostAction(InterfaceEntity interfaceEntity, JSONObject header, JSONObject body, Logger logger){
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        Map result = new HashMap();
        PostMethod postMethod = new PostMethod(interfaceEntity.getInterfaceProtocol()+"://"+interfaceEntity.getInterfaceHost()+":"+interfaceEntity.getInterfacePort()+interfaceEntity.getInterfaceAddress());
        if(header!=null){
            Set headerKeys = header.keySet();
            for (Object headerKey : headerKeys) {
                postMethod.addRequestHeader(headerKey.toString(), header.get(headerKey).toString());
            }
        }
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        postMethod.addRequestHeader("Content-Type", "application/json;charset=utf-8");
        postMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        logger.info("["+new Date()+"]Start Calling interface: Method=POST; InterfaceName="+interfaceEntity.getInterfaceName()+"; InterfaceHost="+interfaceEntity.getInterfaceHost()+"; InterfacePort="+interfaceEntity.getInterfacePort()+"; InterfaceAddress="+interfaceEntity.getInterfaceAddress());
        long start = System.currentTimeMillis();
        try{
            postMethod.setRequestEntity(new StringRequestEntity(body.toString(), "application/json", "UTF-8"));
            httpClient.executeMethod(postMethod);
            result.put("data",postMethod.getResponseBodyAsString());
            result.put("code","200");
            logger.info("["+new Date()+"]Call interface successful,response: "+postMethod.getResponseBodyAsString());
        }catch (Exception e){
            result.put("code","500");
            result.put("msg","system error");
            logger.warning("["+new Date()+"]Call interface fail,reason: "+e);
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        logger.info("["+new Date()+"]Call interface successfully completed in "+timeElapsed+" ms");
        result.put("timeElapsed",timeElapsed);
        return result;
    }
    public Map GetAction(InterfaceEntity interfaceEntity, JSONObject header, JSONObject body, Logger logger){
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        Map result = new HashMap();
        GetMethod getMethod = new GetMethod(interfaceEntity.getInterfaceProtocol()+"://"+interfaceEntity.getInterfaceHost()+":"+interfaceEntity.getInterfacePort()+interfaceEntity.getInterfaceAddress());
        if(header!=null){
            Set headerKeys = header.keySet();
            for (Object headerKey : headerKeys) {
                getMethod.addRequestHeader(headerKey.toString(), header.get(headerKey).toString());
            }
        }
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
        getMethod.addRequestHeader("Content-Type", "application/json;charset=utf-8");
        getMethod.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        HttpMethodParams params = new HttpMethodParams();
        if(body!=null){
            Set bodyKeys = body.keySet();
            for (Object bodyKey : bodyKeys) {
                params.setParameter(bodyKey.toString(),body.get(bodyKey).toString());
            }
        }
        getMethod.setParams(params);
        logger.info("["+new Date()+"]Start Calling interface: Method=GET; InterfaceName="+interfaceEntity.getInterfaceName()+"; InterfaceHost="+interfaceEntity.getInterfaceHost()+"; InterfacePort="+interfaceEntity.getInterfacePort()+"; InterfaceAddress="+interfaceEntity.getInterfaceAddress());
        long start = System.currentTimeMillis();
        try {
            httpClient.executeMethod(getMethod);
            String response = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream()))
                    .lines().parallel().collect(Collectors.joining(System.lineSeparator()));
            result.put("data",response);
            result.put("code","200");
            logger.info("["+new Date()+"]Call interface successful,response: "+response);
        } catch (IOException e) {
            result.put("code","500");
            result.put("msg","system error");
            logger.warning("["+new Date()+"]Call interface fail,reason: "+e);
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        logger.info("["+new Date()+"]Call interface successfully completed in "+timeElapsed+" ms");
        result.put("timeElapsed",timeElapsed);
        return result;
    }
}
