package org.example.ec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @auther cssly
 * @create 2020/6/25 14:10
 */
@Configuration
public class SpringSessionConfig {
    /**
     * 更换序列化器
     * @return
     */
    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer setSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
