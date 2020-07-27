package work.kaiyu.learnsc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auther cssly
 * @create 2020/6/20 1:55
 */
@Configuration
@MapperScan({"work.kaiyu.learnsc.dao"})
public class MyBatisConfig {
}
