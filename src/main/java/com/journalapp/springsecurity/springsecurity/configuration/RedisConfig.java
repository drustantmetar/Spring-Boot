package com.journalapp.springsecurity.springsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate <String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        {
            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            return redisTemplate;

            // After the setting up serializer if we can set the key and value through
            // redis-cli those we can get it from spring boot and vice - versa
            // After above the configuration the serizalition and de-serialization is set

        }
    }
}
