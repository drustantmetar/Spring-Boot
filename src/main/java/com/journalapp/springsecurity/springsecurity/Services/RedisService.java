package com.journalapp.springsecurity.springsecurity.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public <T> T get(String key, Class<T> entityClass) {
        try {
            String value = redisTemplate.opsForValue().get(key);
            if (value == null) {
                log.warn("No value found in Redis for key: {}", key);
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(value, entityClass);
        } catch (Exception e) {
            log.error("Exception in get Redis method :: {}", e.getMessage());
            return null;
        }
    }


    // ttl = time to leave (if ttl-1 then it will never expire)
    public void  set(String key, Object o ,Long ttl)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValues = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key,jsonValues,ttl, TimeUnit.SECONDS);
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
        }
    }
}
