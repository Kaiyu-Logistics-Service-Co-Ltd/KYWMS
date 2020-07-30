package org.example.ec.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther cssly
 * @create 2020/6/14 0:05
 */
@Slf4j
@RefreshScope //支持Nacos的动态刷新功能
@RestController
public class NacosConfigController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/nacos/config/info")
    public String getConfigInfo(){
        return configInfo;
    }
}
