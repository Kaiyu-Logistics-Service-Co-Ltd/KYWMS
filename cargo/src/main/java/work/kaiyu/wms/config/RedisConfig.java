package work.kaiyu.wms.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 配置序列化器
 *
 * @author GLZA
 *
 */
@Configuration
public class RedisConfig {
    /**
     * 配置RedisTemplate 序列化器
     */
    @Bean
    public RedisTemplate<String, Object> setRedisTemplate(RedisConnectionFactory factory) {
        // 创建RedisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 设置序列化器
        // 创建Redis 中的value 的序列化器
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();//json转换的核心对象
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);//设置属性可见,设置json自动检;
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL);
        //objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);//开启默认的类型#已弃用。。。
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 创建Redis 中的key 的序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 设置Redis 中的String 类型的value 的序列化器
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // 设置Redis 中的Hash 类型的value 的序列化器
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        // 设置Redis 中的String 类型的key 的序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 设置Redis 中的Hash 类型的key 的序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.afterPropertiesSet();//创建对象后,对其属性做设置
        return redisTemplate;
    }

}


