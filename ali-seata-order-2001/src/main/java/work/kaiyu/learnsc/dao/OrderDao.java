package work.kaiyu.learnsc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import work.kaiyu.learnsc.domain.Order;

/**
 * @auther cssly
 * @create 2020/6/19 1:07
 */
@Mapper
public interface OrderDao {
    //①新建订单
    void create(Order order);
    //②修改订单状态
    void update(@Param("userId")Long userId,@Param("status")Integer status);
}
