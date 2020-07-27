package work.kaiyu.learnsc.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import work.kaiyu.learnsc.dao.OrderDao;
import work.kaiyu.learnsc.domain.Order;
import work.kaiyu.learnsc.service.AccountService;
import work.kaiyu.learnsc.service.OrderService;
import work.kaiyu.learnsc.service.StorageService;

import javax.annotation.Resource;

/**
 * @auther cssly
 * @create 2020/6/19 1:24
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "ky_create_order",rollbackFor = Exception.class,timeoutMills = 100000)
    public void create(Order order) {
        log.info("--------------->开始新建订单<------------");
        //新建订单
        orderDao.create(order);
        log.info("=============>订单微服务开始调用库存==>扣减Count=>BEGIN");
        //扣减库存
        log.info("=============实体类=====》》》"+order.toString());
        log.info(storageService.decrease(order.getProductId(), order.getCount()).toString());
        log.info("=============>订单微服务开始调用库存==>扣减Count=>END");
        log.info("=============>订单微服务开始调用账户==>扣减Money=>BEGIN");
        //扣减账户余额
        log.info(accountService.decrease(order.getUserId(), order.getMoney()).toString());
        log.info("=============>订单微服务开始调用账户==>扣减Money=>END");
        log.info("=============>订单微服务开始修改订单状态==>BEGIN");
        //修改订单状态0=>1
        orderDao.update(order.getUserId(),0);
        log.info("=============>订单微服务开始修改订单状态==>END");
        log.info("--------------->下订单成功<------------");
    }

}

