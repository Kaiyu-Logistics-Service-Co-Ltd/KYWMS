package work.kaiyu.learnsc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @auther cssly
 * @create 2020/6/20 13:35
 */
@Mapper
public interface AccountDao {
    /**
     * 扣减账户余额
     */
    void decrease(@Param("userId")Long userId, @Param("money") BigDecimal money);
}
