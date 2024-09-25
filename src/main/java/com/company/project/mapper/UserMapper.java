package com.company.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.project.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2024/9/25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    Integer addUser(User user);
}
