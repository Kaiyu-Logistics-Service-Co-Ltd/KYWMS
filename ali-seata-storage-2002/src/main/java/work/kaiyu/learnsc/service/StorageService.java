package work.kaiyu.learnsc.service;

/**
 * @auther cssly
 * @create 2020/6/20 3:25
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId,Integer count);
}
