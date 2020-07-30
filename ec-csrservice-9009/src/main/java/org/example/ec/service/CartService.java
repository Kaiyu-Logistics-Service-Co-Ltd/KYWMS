package org.example.ec.service;

import org.example.ec.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther cssly
 * @create 2020/6/26 15:36
 */
@FeignClient(value = "ec-cart")
public interface CartService {
    @GetMapping("/cart/testFeignCart")
    CommonResult testFeignCart(@RequestParam(value = "go")Integer i);
}
