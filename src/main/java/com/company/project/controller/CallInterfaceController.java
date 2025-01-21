package com.company.project.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.company.project.service.CallInterfaceService;
import com.company.project.service.InterfaceService;
import com.company.project.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CallInterfaceController {
    @Autowired
    private CallInterfaceService callInterfaceService;
    @Autowired
    private InterfaceService interfaceService;

    @PostMapping("with_header/{interfaceCode}")
    public JSONObject callInterfaceWithHeader(@RequestBody JSONObject headerAndBody, @PathVariable String interfaceCode) {
        JSONObject header = headerAndBody.getJSONObject("header");
        JSONObject body = headerAndBody.getJSONObject("body");
        interfaceService.parseInterfaceHeaderToList(interfaceCode,header);
        interfaceService.parseInterfaceBodyToList(interfaceCode,body,null,null);
        interfaceService.parseInterfaceBodyToJSONObject(interfaceCode);
        return callInterfaceService.CallInterface(interfaceCode,header,body);
    }
    @PostMapping("/without_header/{interfaceCode}")
    public JSONObject callInterfaceWithoutHeader(@RequestBody JSONObject body, @PathVariable String interfaceCode) {

        return null;
    }
}
