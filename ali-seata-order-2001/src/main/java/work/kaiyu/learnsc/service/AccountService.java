package work.kaiyu.learnsc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.kaiyu.learnsc.entities.CommonResult;

import java.math.BigDecimal;

/**
 * @auther cssly
 * @create 2020/6/19 1:23
 */
@FeignClient(value = "ali-seata-account")
public interface AccountService {
    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId")Long userId, @RequestParam("money")BigDecimal money);
}
