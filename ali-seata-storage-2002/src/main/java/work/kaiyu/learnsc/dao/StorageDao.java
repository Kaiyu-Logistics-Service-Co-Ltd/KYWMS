package work.kaiyu.learnsc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * @auther cssly
 * @create 2020/6/20 3:18
 */
@Mapper
public interface StorageDao {
    //扣减库存
    void decrease(@Param("productId")Long productId, @Param("count")Integer count);
}
