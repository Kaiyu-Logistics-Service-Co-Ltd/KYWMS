package work.kaiyu.wms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.domain.Company;

@FeignClient(value = "wms-company")
public interface CompanyService {
    @GetMapping("/queryAllCompany")
    public CommonResult queryAllCompany();
    @GetMapping("/queryCompany")
    public CommonResult queryCompany(@RequestParam("companyId") Long companyId);
    @PostMapping("/insertCompany")
    public CommonResult insertCompany(@RequestBody Company company);
}
