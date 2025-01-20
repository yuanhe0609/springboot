package com.company.project.service;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface CallInterfaceService {
    /**
     * @description 调用外部接口
     * @param interfaceCode 接口编码
     * @param header 请求头
     * @param body 请求正文
     * @return 返回接口返回值
     * */
    JSONObject CallInterface(String interfaceCode, JSONObject header, JSONObject body);
}
