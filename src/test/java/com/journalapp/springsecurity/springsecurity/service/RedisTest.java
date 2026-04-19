package com.journalapp.springsecurity.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    void testEmail()
    {
        redisTemplate.opsForValue().set("email","drustant.metar@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a =1;
    }

}
