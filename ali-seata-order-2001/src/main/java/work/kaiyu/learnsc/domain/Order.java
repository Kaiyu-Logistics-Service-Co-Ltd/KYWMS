package work.kaiyu.learnsc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @auther cssly
 * @create 2020/6/19 0:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long productId;
    private Long userId;
    private Integer count;
    private BigDecimal money;
    /**
     * 订单状态:0==>创建中;1==>已创建
     */
    private Integer status;
}
