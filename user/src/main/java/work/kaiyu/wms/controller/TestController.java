package work.kaiyu.wms.controller;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.domain.User;
import work.kaiyu.wms.service.UserService;
import work.kaiyu.wms.utils.AESEncrypt;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
public class TestController {
    @Resource
    private UserService userService;

    private Integer LOGIN_TYPE=1;

    @GetMapping("/queryUser")
    public CommonResult queryUser(@RequestParam Integer queryType){
        try {
            return new CommonResult(200,"Success!",userService.queryAllUser(queryType));
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error!");
        }
    }

    @PostMapping(value = "/login")
    public CommonResult login(@RequestBody User user,
                              HttpSession session){
        try{
            User currentUser = userService.checkUserLogin(user.getUserCode(),user.getUserPassword(),LOGIN_TYPE);
            if (currentUser != null){
                if (currentUser.getUserCode().equals(user.getUserCode())&&
                        currentUser.getUserPassword().equals(AESEncrypt.AESEncode(user.getUserPassword()))){
                    session.setAttribute("currentUser",currentUser);
                    return new CommonResult(200,"登录成功!",currentUser);
                }else {
                    return new CommonResult(401,"用户名或密码不正确!");
                }
            }else{
                return new CommonResult(401,"用户名或密码不正确!");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error!");
        }
    }
    @PostMapping(value = "/addUser")
    public CommonResult addUser(@RequestBody User user,
                                 HttpSession session){
        try{
            User currentUser = (User) session.getAttribute("currentUser");

            Integer regFlag = userService.regUser(user,0L);
            if (regFlag == 1){
                return new CommonResult(200,"注册成功!");

            }else{
                return new CommonResult(401,"注册失败!");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error!");
        }
    }

    @PostMapping("/getSession")
    public CommonResult<User> getSession(HttpSession session,
                                         HttpServletRequest request) {
        try {
            User sessionUser = (User) session.getAttribute("currentUser");
            if (!ObjectUtil.isNull(sessionUser)) {
                return new CommonResult(200, "获取登录信息成功", sessionUser);
            } else {
                return new CommonResult(401, "请登录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(500, "Error");
        }
    }

    @PostMapping("/removeSession")
    public CommonResult<User> removeSession(HttpSession session,
                                         HttpServletRequest request) {
        try {
            session.removeAttribute("currentUser");
            User sessionUser = (User) session.getAttribute("currentUser");
            if (ObjectUtil.isNull(sessionUser)) {
                return new CommonResult(200, "登出成功");
            } else {
                return new CommonResult(401, "登出失败",sessionUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(500, "Error");
        }
    }
}
