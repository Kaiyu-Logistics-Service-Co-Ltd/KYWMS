package work.kaiyu.learnsc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.kaiyu.learnsc.entities.CommonResult;
import work.kaiyu.learnsc.service.StorageService;

import javax.annotation.Resource;

/**
 * @auther cssly
 * @create 2020/6/20 3:31
 */
@RestController
public class StorageController {
    @Resource
    private StorageService storageService;
    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(@RequestParam("productId")Long productId,@RequestParam("count")Integer count){
        storageService.decrease(productId,count);
        return new CommonResult(200,"扣减库存成功!");
    }
}
