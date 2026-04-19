package com.journalapp.springsecurity.springsecurity.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
public class RedisTests {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Disabled
    @Test
    void redisTest()
    {
        redisTemplate.opsForValue().set("myMail","drustant.metar@gmail.com");
        String email = redisTemplate.opsForValue().get("salary");
        System.out.println(email);
    }
}
