package com.company.project.service.impl;

import com.company.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2024/9/25
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


}
