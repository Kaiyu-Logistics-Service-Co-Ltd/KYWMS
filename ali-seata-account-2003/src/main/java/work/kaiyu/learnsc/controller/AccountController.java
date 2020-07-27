package work.kaiyu.learnsc.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.kaiyu.learnsc.entities.CommonResult;
import work.kaiyu.learnsc.service.AccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @auther cssly
 * @create 2020/6/20 13:34
 */
@Slf4j
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    /**
     * 扣减账户余额
     * @param userId
     * @param money
     * @return
     */
    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId")Long userId, @RequestParam("money")BigDecimal money){
        int i = 10/0;
        accountService.decrease(userId,money);
        return new CommonResult(200,"扣减账户余额成功");
    }
}
