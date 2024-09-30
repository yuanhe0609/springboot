package com.company.project.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2024/9/30
 */
@Configuration
public class MyRedis {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 设置其他的k-v的默认的序列化
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer(Object.class));
        //单独设置k的序列化
        template.setKeySerializer(new StringRedisSerializer());

        return template;
    }
}

