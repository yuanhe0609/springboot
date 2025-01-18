package com.company.project.controller;

import com.company.project.entity.InterfaceEntity;
import com.company.project.service.InterfaceService;
import com.company.project.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interface")
public class InterfaceController {

    @Autowired
    private InterfaceService interfaceService;

    @RequestMapping("/add")
    public ResultVo<?> addInterface(@RequestBody InterfaceEntity interfaceEntity) {
        return ResultVo.ok("成功新增"+interfaceService.addNewInterface(interfaceEntity)+"条接口");
    }
    @RequestMapping("/list")
    public ResultVo<?> getInterfaceList() {
        return ResultVo.ok(interfaceService.getAllInterfaceList());
    }
    @RequestMapping("/update")
    public ResultVo<?> updateInterface(@RequestBody InterfaceEntity interfaceEntity) {
        return ResultVo.ok("成功更新"+interfaceService.updateInterface(interfaceEntity)+"条接口");
    }
    @RequestMapping("/delete")
    public ResultVo<?> deleteInterface(@RequestParam(name="interfaceCode") String interfaceCode) {
        return ResultVo.ok("成功删除"+interfaceService.deleteInterface(interfaceCode)+"条接口");
    }

}
