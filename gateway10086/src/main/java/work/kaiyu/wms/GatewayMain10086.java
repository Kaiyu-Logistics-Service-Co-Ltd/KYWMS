package work.kaiyu.wms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayMain10086 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain10086.class, args);
    }
}
