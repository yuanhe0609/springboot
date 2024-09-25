package com.company.project.controller;

import com.company.project.entity.User;
import com.company.project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2024/9/25
 */
@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/")
    public String hello(){
        User user = new User();
        user.setName("test01");
        System.out.println(user);
        userMapper.addUser(user);
        return "forward:index.html";
    }
}
