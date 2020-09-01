package work.kaiyu.wms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.domain.User;

@FeignClient(value = "wms-user")
public interface UserService {
    @PostMapping("/user/getSession")
    public CommonResult<User> getSession();
}
