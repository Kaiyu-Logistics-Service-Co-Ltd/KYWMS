package work.kaiyu.learnsc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @auther cssly
 * @create 2020/6/20 13:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 总额度
     */
    private BigDecimal total;
    /**
     * 已用额度
     */
    private BigDecimal used;
    /**
     * 剩余额度
     */
    private BigDecimal residue;
}
