package work.kaiyu.learnsc.service;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @auther cssly
 * @create 2020/6/20 13:35
 */
public interface AccountService {
    void decrease(Long userId,BigDecimal money);

}
