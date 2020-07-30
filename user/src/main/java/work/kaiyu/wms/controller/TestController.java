package work.kaiyu.wms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.service.UserService;

import javax.annotation.Resource;

@Slf4j
@RestController
public class TestController {
    @Resource
    private UserService userService;
    @GetMapping("/queryUser")
    public CommonResult queryUser(){
        return new CommonResult(200,"Success",userService.queryAllUser(1));
    }
}
