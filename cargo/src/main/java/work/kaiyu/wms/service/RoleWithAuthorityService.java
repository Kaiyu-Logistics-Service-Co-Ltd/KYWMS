package work.kaiyu.wms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.domain.RoleAndAuthority;

@FeignClient(value = "wms-role-with-authority")
public interface RoleWithAuthorityService {
    @GetMapping("/rwa/viewUserRoleWithAuthority")
    CommonResult<RoleAndAuthority> viewUserRoleWithAuthority();
}
