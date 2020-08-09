package work.kaiyu.wms.service;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@FeignClient(value = "wms-user")
public interface UserService {
    @PostMapping("/user/getSession")
    public CommonResult<User> getSession();
}
