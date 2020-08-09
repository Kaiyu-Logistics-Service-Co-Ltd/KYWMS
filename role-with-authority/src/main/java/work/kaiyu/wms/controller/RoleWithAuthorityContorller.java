package work.kaiyu.wms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.domain.Department;
import work.kaiyu.wms.domain.RoleAndAuthority;
import work.kaiyu.wms.domain.UserRole;
import work.kaiyu.wms.service.RoleWithAuthorityService;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rwa")
public class RoleWithAuthorityContorller {

    @Resource
    private RoleWithAuthorityService roleWithAuthorityService;

    @GetMapping("/viewUserRoleWithAuthority")
    public CommonResult viewUserRoleWithAuthority(){
        try {
            final RoleAndAuthority roleAndAuthority = roleWithAuthorityService.selectCurrentUserRAByPrimaryKey();
            if (roleAndAuthority != null){
                return new CommonResult(200,"获取成功",roleAndAuthority);
            }else {
                return new CommonResult(400,"获取失败");
            }
        }catch (Exception e){
            return new CommonResult(500,"Error");
        }
    }
    @PostMapping("/addUserRoleWithAuthority")
    public CommonResult addUserRoleWithAuthority(@RequestBody RoleAndAuthority roleAndAuthority){
        try {
            Integer addFlag = 0;
            addFlag = roleWithAuthorityService.insertRoleAndAuthority(roleAndAuthority);
            if (addFlag!=0){
                if (addFlag==200){
                    return new CommonResult(addFlag,"添加角色成功");
                }else if (addFlag==300){
                    return new CommonResult(addFlag,"用户权限不足");
                }else if (addFlag==400){
                    return new CommonResult(addFlag,"获取当前用户权限:FAIL");
                }else if (addFlag==403){
                    return new CommonResult(addFlag,"获取当前用户Session:FAIL");
                }else if (addFlag==500){
                    return new CommonResult(addFlag,"服务器错误");
                }
            }
            return new CommonResult(500,"未知Error");
        }catch (Exception e){
            return new CommonResult(500,"Error");
        }
    }
    @GetMapping("/showAllRoles")
    public CommonResult showAllRoles(){
        try {
            List<UserRole> userRoleList = roleWithAuthorityService.selectSimpleRoles();
            if (userRoleList!=null&&!userRoleList.isEmpty()){
                return new CommonResult(200,"查询角色成功",userRoleList);
            }
            return new CommonResult(404,"此用户未创建角色");
        }catch (Exception e){
            return new CommonResult(500,"Error");
        }
    }
    @GetMapping("/showAllDepartments")
    public CommonResult showAllDepartments(){
        try {
            List<Department> departmentList = roleWithAuthorityService.selectSimpleDepartments();
            if (departmentList!=null&&!departmentList.isEmpty()){
                return new CommonResult(200,"查询部门成功",departmentList);
            }
            return new CommonResult(404,"查询部门失败");
        }catch (Exception e){
            return new CommonResult(500,"Error");
        }
    }
}
