package work.kaiyu.learnsc.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import work.kaiyu.learnsc.dao.StorageDao;
import work.kaiyu.learnsc.service.StorageService;

import javax.annotation.Resource;

/**
 * @auther cssly
 * @create 2020/6/20 3:26
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;
    /**
     * 扣减库存
     */
    @Override
    @GlobalTransactional(name = "ky_create_order",rollbackFor = Exception.class,timeoutMills = 100000)
    public void decrease(Long productId, Integer count) {
        LOGGER.info("========>storage-service==>扣减库存开始");
        storageDao.decrease(productId,count);
        LOGGER.info("========>storage-service==>扣减库存结束");
    }

}
