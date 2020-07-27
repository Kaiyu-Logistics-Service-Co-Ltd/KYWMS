package work.kaiyu.learnsc.service;

import org.apache.ibatis.annotations.Param;
import work.kaiyu.learnsc.domain.Order;

/**
 * @auther cssly
 * @create 2020/6/19 1:22
 */
public interface OrderService {
    void create(Order order);
}
