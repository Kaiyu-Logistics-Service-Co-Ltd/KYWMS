package work.kaiyu.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableFeignClients
@EnableDiscoveryClient
@EnableRedisHttpSession
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CompanyMain10011 {
    public static void main(String[] args) {
        SpringApplication.run(CompanyMain10011.class, args);
    }
}
