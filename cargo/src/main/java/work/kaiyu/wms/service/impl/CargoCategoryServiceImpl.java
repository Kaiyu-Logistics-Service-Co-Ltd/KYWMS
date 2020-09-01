package work.kaiyu.wms.service.impl;

import cn.hutool.core.lang.UUID;
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
                RoleAndAuthority roleAndAuthority = RACommonResult.getData();
                Authority currentAuthority = roleAndAuthority.getAuthority();
                /**
                 * 判断是否有对应权限
                 */
                if (currentAuthority!=null||currentAuthority.getAuthorityId()!=0){
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
                            return 200;
                        }
                        return 205;
                    }else{
                        log.error("================================<用户权限不足:FAIL");
                        return 300;
                    }
                }else{
                    log.error("================================<获取当前用户权限:FAIL");
                    return 400;
                }
            }else {
                log.error("================================<获取当前用户权限:FAIL");
                return 404;
            }
        }else{
            log.error("================================<获取当前用户Session:FAIL");
            return 403;
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
                RoleAndAuthority roleAndAuthority = RACommonResult.getData();
                Authority currentAuthority = roleAndAuthority.getAuthority();
                /**
                 * 判断是否有对应权限
                 */
                if (currentAuthority!=null||currentAuthority.getAuthorityId()!=0){
                    if (currentAuthority.getDeleteCargoCategory()==true){
                        /**
                         * 数据处理
                         */
                        Integer deleteFlag = cargoCategoryDao.deleteCargoCategory(cargoCategoryId);
                        if (deleteFlag==1){
                            return 200;
                        }
                        return 205;
                    }else{
                        log.error("================================<用户权限不足:FAIL");
                        return 300;
                    }
                }else{
                    log.error("================================<获取当前用户权限:FAIL");
                    return 400;
                }
            }else {
                log.error("================================<获取当前用户权限:FAIL");
                return 404;
            }
        }else{
            log.error("================================<获取当前用户Session:FAIL");
            return 403;
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
                RoleAndAuthority roleAndAuthority = RACommonResult.getData();
                Authority currentAuthority = roleAndAuthority.getAuthority();
                /**
                 * 判断是否有对应权限
                 */
                if (currentAuthority!=null||currentAuthority.getAuthorityId()!=0){
                    if (currentAuthority.getUpdateCargoCategory()==true){
                        /**
                         * 数据处理
                         */
                        Integer updateFlag = cargoCategoryDao.updateCargoCategory(cargoCategory);
                        if (updateFlag==1){
                            return 200;
                        }
                        return 205;
                    }else{
                        log.error("================================<用户权限不足:FAIL");
                        return 300;
                    }
                }else{
                    log.error("================================<获取当前用户权限:FAIL");
                    return 400;
                }
            }else {
                log.error("================================<获取当前用户权限:FAIL");
                return 404;
            }
        }else{
            log.error("================================<获取当前用户Session:FAIL");
            return 403;
        }
    }

    @Override
    public List<CargoCategory> getCargoCategoryListByParentId(Long cargoCategoryParentId) {
        return cargoCategoryDao.getCargoCategoryListByParentId(cargoCategoryParentId);
    }
}
