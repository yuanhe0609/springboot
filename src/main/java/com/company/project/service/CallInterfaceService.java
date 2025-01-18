package com.company.project.service;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface CallInterfaceService {
    Object CallInterface(String interfaceCode, JSONObject header, JSONObject body);
}
