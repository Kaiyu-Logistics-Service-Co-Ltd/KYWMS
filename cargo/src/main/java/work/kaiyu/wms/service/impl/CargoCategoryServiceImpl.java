package work.kaiyu.wms.service.impl;

import cn.hutool.http.HttpStatus;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import work.kaiyu.wms.dao.CargoCategoryDao;
import work.kaiyu.wms.domain.*;
import work.kaiyu.wms.service.CargoCategoryService;
import work.kaiyu.wms.service.RoleWithAuthorityService;
import work.kaiyu.wms.service.UserService;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class CargoCategoryServiceImpl implements CargoCategoryService {

    @Resource
    private CargoCategoryDao cargoCategoryDao;

    @Resource
    private UserService userService;
    @Resource
    private RoleWithAuthorityService roleWithAuthorityService;

    @Override
    @GlobalTransactional
    public Integer addCargoCategory(CargoCategory cargoCategory) {
        CommonResult<User> userCommonResult = userService.getSession();
        if (userCommonResult.getCode()==200){
            log.info("===============================>获取当前用户Session:SUCCESS");
            /**
             * 数据处理
             */
            User currentUser = userCommonResult.getData();
            log.info("===============================>当前用户:"+currentUser.toString());
            log.info("===============================>获取当前用户权限:BEGIN");
            CommonResult<RoleAndAuthority> RACommonResult = roleWithAuthorityService.viewUserRoleWithAuthority();
            if (RACommonResult.getCode()==200){
                Authority currentAuthority = RACommonResult.getData().getAuthority();
                /**
                 * 判断是否有对应权限
                 */
                if (currentAuthority!=null&&!currentAuthority.getAuthorityId().equals(0L)){
                    /**
                     * 判断是否有修改权限
                     */
                    if (currentAuthority.getInsertCargoCategory()==true){
                        /**
                         * 数据处理
                         */
                        cargoCategory.setCargoCategoryCreatedBy(currentUser.getUserId());
                        Integer addFlag = cargoCategoryDao.addCargoCategory(cargoCategory);
                        if (addFlag==1){
                            return HttpStatus.HTTP_OK;
                        }
                        return HttpStatus.HTTP_RESET;
                    }else{
                        log.error("================================<用户权限不足:FAIL");
                        return HttpStatus.HTTP_UNAUTHORIZED;
                    }
                }else{
                    log.error("================================<获取当前用户权限:FAIL");
                    return HttpStatus.HTTP_FORBIDDEN;
                }
            }else {
                log.error("================================<获取当前用户权限:FAIL");
                return HttpStatus.HTTP_NO_CONTENT;
            }
        }else{
            log.error("================================<获取当前用户Session:FAIL");
            return HttpStatus.HTTP_FORBIDDEN;
        }
    }

    @Override
    public Integer deleteCargoCategory(Long cargoCategoryId) {
        CommonResult<User> userCommonResult = userService.getSession();
        if (userCommonResult.getCode()==200){
            log.info("===============================>获取当前用户Session:SUCCESS");
            /**
             * 数据处理
             */
            User currentUser = userCommonResult.getData();
            log.info("===============================>当前用户:"+currentUser.toString());
            log.info("===============================>获取当前用户权限:BEGIN");
            CommonResult<RoleAndAuthority> RACommonResult = roleWithAuthorityService.viewUserRoleWithAuthority();
            if (RACommonResult.getCode()==200){
                Authority currentAuthority = RACommonResult.getData().getAuthority();
                /**
                 * 判断是否有对应权限
                 */
                if (currentAuthority!=null&&!currentAuthority.getAuthorityId().equals(0L)){
                    if (currentAuthority.getDeleteCargoCategory()==true){
                        /**
                         * 数据处理
                         */
                        Integer deleteFlag = cargoCategoryDao.deleteCargoCategory(cargoCategoryId);
                        if (deleteFlag==1){
                            return HttpStatus.HTTP_OK;
                        }
                        return HttpStatus.HTTP_RESET;
                    }else{
                        log.error("================================<用户权限不足:FAIL");
                        return HttpStatus.HTTP_UNAUTHORIZED;
                    }
                }else{
                    log.error("================================<获取当前用户权限:FAIL");
                    return HttpStatus.HTTP_FORBIDDEN;
                }
            }else {
                log.error("================================<获取当前用户权限:FAIL");
                return HttpStatus.HTTP_NO_CONTENT;
            }
        }else{
            log.error("================================<获取当前用户Session:FAIL");
            return HttpStatus.HTTP_FORBIDDEN;
        }
    }

    @Override
    public Integer updateCargoCategory(CargoCategory cargoCategory) {
        CommonResult<User> userCommonResult = userService.getSession();
        if (userCommonResult.getCode()==200){
            log.info("===============================>获取当前用户Session:SUCCESS");
            /**
             * 数据处理
             */
            User currentUser = userCommonResult.getData();
            log.info("===============================>当前用户:"+currentUser.toString());
            log.info("===============================>获取当前用户权限:BEGIN");
            CommonResult<RoleAndAuthority> RACommonResult = roleWithAuthorityService.viewUserRoleWithAuthority();
            if (RACommonResult.getCode()==200){
                Authority currentAuthority = RACommonResult.getData().getAuthority();
                /**
                 * 判断是否有对应权限
                 */
                if (currentAuthority!=null&&!currentAuthority.getAuthorityId().equals(0L)){
                    if (currentAuthority.getUpdateCargoCategory()==true){
                        /**
                         * 数据处理
                         */
                        Integer updateFlag = cargoCategoryDao.updateCargoCategory(cargoCategory);
                        if (updateFlag==1){
                            return HttpStatus.HTTP_OK;
                        }
                        return HttpStatus.HTTP_RESET;
                    }else{
                        log.error("================================<用户权限不足:FAIL");
                        return HttpStatus.HTTP_UNAUTHORIZED;
                    }
                }else{
                    log.error("================================<获取当前用户权限:FAIL");
                    return HttpStatus.HTTP_FORBIDDEN;
                }
            }else {
                log.error("================================<获取当前用户权限:FAIL");
                return HttpStatus.HTTP_NO_CONTENT;
            }
        }else{
            log.error("================================<获取当前用户Session:FAIL");
            return HttpStatus.HTTP_FORBIDDEN;
        }
    }

    @Override
    public List<CargoCategory> getCargoCategoryListByParentId(Long cargoCategoryParentId) {
        return cargoCategoryDao.getCargoCategoryListByParentId(cargoCategoryParentId);
    }
}
