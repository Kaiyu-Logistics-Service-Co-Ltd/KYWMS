package work.kaiyu.wms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.domain.Resource;
import work.kaiyu.wms.service.UserResourceService;
import java.io.IOException;
@Slf4j
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @javax.annotation.Resource
    private UserResourceService userResourceService;

    @GetMapping("/ping")
    public CommonResult ping(){
        return new CommonResult(200,"PONG");
    }
    @PostMapping("/uploadAvatar")
    public CommonResult uploadAvatar(@RequestParam("avatar") MultipartFile avatar){
        Boolean flag = userResourceService.insertResource(1, avatar);

        if (flag){
         return new CommonResult(200,"上传头像成功");
        }
        return new CommonResult(500,"上传头像失败");
    }
    @PostMapping("/uploadFile")
    public CommonResult uploadFile(@RequestParam("avatar") MultipartFile avatar) throws IOException{
        return null;
    }
    @PostMapping("/createResource")
    public CommonResult createResource(@RequestParam("type") Integer type){
        Boolean flag = userResourceService.createResource(type);
        if (flag){
            return new CommonResult(200,"创建成功");
        }
        return new CommonResult(500,"创建失败");
    }
    @GetMapping("/selectUserResource")
    public CommonResult selectUserResource(@RequestParam("resourceType") Integer resourceType){
        Resource resource = userResourceService.selectOneResource(resourceType);
        if (resource!=null){
            return new CommonResult(200,"获取成功",resource);
        }else {
            return new CommonResult(500,"获取失败");
        }
    }
}
