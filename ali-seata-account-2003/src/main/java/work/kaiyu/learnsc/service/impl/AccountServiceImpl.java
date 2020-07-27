package work.kaiyu.learnsc.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import work.kaiyu.learnsc.dao.AccountDao;
import work.kaiyu.learnsc.service.AccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @auther cssly
 * @create 2020/6/20 13:35
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private AccountDao accountDao;

    /**
     * 扣减账户余额
     * @param userId
     * @param money
     */
    @Override
    @GlobalTransactional(name = "ky_create_order",rollbackFor = Exception.class,timeoutMills = 100000)
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("======>account-service==>扣减账户余额开始");
        //模拟超时异常。全局事务回滚
        accountDao.decrease(userId,money);
        LOGGER.info("======>account-service==>扣减账户余额结束");
    }
}
