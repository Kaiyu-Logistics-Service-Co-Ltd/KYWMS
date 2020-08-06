package work.kaiyu.wms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.domain.Company;
import work.kaiyu.wms.service.CompanyService;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Resource
    private CompanyService companyService;

    @GetMapping("/queryAllCompany")
    public CommonResult queryAllCompany(){
        try {
            return new CommonResult(200,"Success!",companyService.queryAllCompany());
        }catch (Exception e){
            e.getStackTrace();
            return new CommonResult(500,"Error!");
        }
    }
    @GetMapping("/queryCompany")
    public CommonResult queryCompany(@RequestParam Long companyId){
        try {
            return new CommonResult(200,"Success!",companyService.queryCompanyWithPrimaryKey(companyId));
        }catch (Exception e){
            return new CommonResult(500,"Error!");
        }
    }
    @PostMapping("/insertCompany")
    public CommonResult insertCompany(@RequestBody Company company){
        try {
//            int i=10/0;
            return new CommonResult(200,"Success!",companyService.insertCompany(company));
        }catch (Exception e){
            return new CommonResult(500,"Error!");
        }
    }
}
