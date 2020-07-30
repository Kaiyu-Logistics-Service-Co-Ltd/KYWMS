package org.example.ec.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auther cssly
 * @create 2020/6/20 1:55
 */
@Configuration
@MapperScan({"org.example.ec.dao"})
public class MyBatisConfig {
}
