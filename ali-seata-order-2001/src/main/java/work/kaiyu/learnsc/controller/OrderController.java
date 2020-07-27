package work.kaiyu.learnsc.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import work.kaiyu.learnsc.domain.Order;
import work.kaiyu.learnsc.entities.CommonResult;
import work.kaiyu.learnsc.entities.Payment;
import work.kaiyu.learnsc.service.OrderService;

import javax.annotation.Resource;

/**
 * @auther cssly
 * @create 2020/6/20 1:29
 */
@Slf4j
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
    @GetMapping("/order/create")
    @SentinelResource(value = "createOrder",fallback = "handlerFallback",blockHandler = "blockHandler")
    public CommonResult create(Order order){
        System.out.println("======================================");
        System.out.println(order.toString());
        System.out.println("======================================");
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult handlerFallback(@PathVariable Order order, Throwable e){
        return new CommonResult(444,"兜底异常handlerFallback===>exception==>"+e.getMessage());
    }
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult blockHandler(@PathVariable Order order, BlockException e){
        return new CommonResult(445,"blockHandler-Sentinel限流===>无此流水==>blockException=>"+e.getClass().getCanonicalName()+"服务不可用");
    }
}
