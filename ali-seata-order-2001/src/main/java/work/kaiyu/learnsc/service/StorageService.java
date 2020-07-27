package work.kaiyu.learnsc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.kaiyu.learnsc.entities.CommonResult;

/**
 * @auther cssly
 * @create 2020/6/19 1:23
 */
@FeignClient(value = "ali-seata-storage")
public interface StorageService {
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId")Long productId,@RequestParam("count")Integer count);
}
