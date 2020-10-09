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
public class UserController {
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
    @GetMapping("/ping")
    public CommonResult ping(){
        try {
            return new CommonResult(200,"PONG!");
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
    @PostMapping(value = "/reLogin")
    public CommonResult reLogin(@RequestBody User user,
                              HttpSession session){
        try{
            user.setUserPassword(AESEncrypt.AESDncode(user.getUserPassword()));
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
    @GetMapping("/checkIfTheUserCodeExists")
    public CommonResult checkIfTheUserCodeExists(@RequestParam String userCode){
        try{
            Integer checkFlage = userService.checkIfTheUserCodeExists(userCode);
            if (checkFlage==0){
                log.info("==================>用户名可用");
                return new CommonResult(200,"用户名可用");
            }else return new CommonResult(204,"用户名已被使用");
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
            if (currentUser!=null){
                if (user!=null){
                    Integer addFlag = userService.addUser(user,currentUser);
                    if (addFlag==1){
                        return new CommonResult(200,"添加用户成功!");
                    }else if (addFlag==401){
                        return new CommonResult(addFlag,"无可用角色!");
                    }else if (addFlag==402){
                        return new CommonResult(addFlag,"用户名重复!");
                    }else {
                        return new CommonResult(204,"添加用户失败!");
                    }
                }
                else{
                    return new CommonResult(205,"输入信息有误!");
                }
            }else {
                return new CommonResult(401,"请登录!");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error!");
        }
    }
    @PostMapping(value = "/updateUserInfo")
    public CommonResult updateUserInfo(@RequestBody User user,
                                HttpSession session){
        try{
            User currentUser = (User) session.getAttribute("currentUser");
            if (currentUser!=null){
                if (user!=null){
                    Integer updateFlag = userService.updateUserInfo(user,currentUser);
                    if (updateFlag ==1){
                        return new CommonResult(200,"修改用户成功!");
                    }else if (updateFlag ==401){
                        return new CommonResult(updateFlag,"无可用角色!");
                    }else if (updateFlag ==402){
                        return new CommonResult(updateFlag,"用户名重复!");
                    }else {
                        return new CommonResult(204,"修改用户失败!");
                    }
                }
                else{
                    return new CommonResult(205,"输入信息有误!");
                }
            }else {
                return new CommonResult(401,"请登录!");
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
            User sessionUser = (User) session.getAttribute("currentUser");
            if (ObjectUtil.isNull(sessionUser)) {
                return new CommonResult(404, "未登录");
            } else {
                session.removeAttribute("currentUser");
                User checkUser = (User) session.getAttribute("currentUser");
                if (ObjectUtil.isNull(checkUser)) {
                    return new CommonResult(200, "登出成功");
                } else {
                    return new CommonResult(401, "登出失败",checkUser);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(500, "Error");
        }
    }
    @PostMapping("/userPasswordEncryption")
    public CommonResult userPasswordEncryption(@RequestParam String userPassword){
        String cipherText = AESEncrypt.AESEncode(userPassword);
        /**
         * 判断加密是否成功
         */
        if (cipherText.equals("")||cipherText==null){
            return new CommonResult(204, "加密失败");
        }else {
            return new CommonResult(200, "加密成功",cipherText);
        }
    }
}
