package work.kaiyu.wms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.service.DataDictionaryService;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/dataD")
public class DataDictionaryController {


    @Resource
    private DataDictionaryService dataDictionaryService;
    @GetMapping("/queryDataD")
    public CommonResult queryDataD(){
        try {
            return new CommonResult(200,"Success!",dataDictionaryService.queryAllDataDictionaryForSimple());
        }catch (Exception e){
            return new CommonResult(500,"Error!");
        }
    }
}
