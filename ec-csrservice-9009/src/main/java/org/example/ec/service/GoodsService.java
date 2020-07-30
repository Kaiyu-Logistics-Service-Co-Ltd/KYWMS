package org.example.ec.service;

import org.example.ec.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther cssly
 * @create 2020/6/26 15:38
 */
@FeignClient(value = "ec-goods")
public interface GoodsService {
    @GetMapping("/goods/testFeignGoods")
    CommonResult testFeignGoods(@RequestParam(value = "go")Integer i);
}
