package work.kaiyu.wms.service.impl;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import work.kaiyu.wms.dao.CargoManagementDao;
import work.kaiyu.wms.domain.*;
import work.kaiyu.wms.service.CargoManagementService;
import work.kaiyu.wms.service.RoleWithAuthorityService;
import work.kaiyu.wms.service.UserService;
import work.kaiyu.wms.utils.HttpStatus;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CargoManagementServiceImpl implements CargoManagementService {

    @Resource
    private CargoManagementDao cargoManagementDao;
    @Resource
    private UserService userService;
    @Resource
    private RoleWithAuthorityService roleWithAuthorityService;

    @Override
    public Integer addCargo(Cargo cargo) {
        CommonResult<User> session = userService.getSession();
        if (session.getCode()==200){
            User currentUser = session.getData();
            CommonResult<RoleAndAuthority> result = roleWithAuthorityService.viewUserRoleWithAuthority();
            if (result.getCode()==200){
                Authority authority = result.getData().getAuthority();
                if (authority!=null&&!authority.getAuthorityId().equals(0L)){
                    if (authority.getInsertCargo()){
                        cargo.setCreatedBy(currentUser.getUserId());
                        cargo.setCargoCode("cargo-"+UUID.randomUUID().toString(true).toUpperCase());
                        Integer addFlag = cargoManagementDao.addCargo(cargo);
                        if (addFlag==1){
                            return HttpStatus.HTTP_OK;
                        }else {
                            return HttpStatus.HTTP_RESET;
                        }
                    }else {
                        return HttpStatus.HTTP_UNAUTHORIZED;
                    }
                }else {
                    return HttpStatus.HTTP_FORBIDDEN;
                }
            }else {
                return HttpStatus.HTTP_NO_CONTENT;
            }
        }
        return HttpStatus.HTTP_FORBIDDEN;
    }

    @Override
    public Integer deleteCargo(Long cargoId) {
        CommonResult<RoleAndAuthority> result = roleWithAuthorityService.viewUserRoleWithAuthority();
        if (result.getCode()==200){
            Authority authority = result.getData().getAuthority();
            if (authority!=null&&!authority.getAuthorityId().equals(0L)){
                if (authority.getDeleteCargo()){
                    Integer deleteFlag = cargoManagementDao.deleteCargo(cargoId);
                    if (deleteFlag==1){
                        return HttpStatus.HTTP_OK;
                    }else {
                        return HttpStatus.HTTP_RESET;
                    }
                }else {
                    return HttpStatus.HTTP_UNAUTHORIZED;
                }
            }else {
                return HttpStatus.HTTP_FORBIDDEN;
            }
        }else {
            return HttpStatus.HTTP_NO_CONTENT;
        }
    }

    @Override
    public Integer updateCargo(Cargo cargo) {
        CommonResult<User> session = userService.getSession();
        if (session.getCode()==200){
            User currentUser = session.getData();
            CommonResult<RoleAndAuthority> result = roleWithAuthorityService.viewUserRoleWithAuthority();
            if (result.getCode()==200){
                Authority authority = result.getData().getAuthority();
                if (authority!=null&&!authority.getAuthorityId().equals(0L)){
                    if (authority.getInsertCargo()){
                        cargo.setModifyBy(currentUser.getUserId());
                        cargo.setModifyDate(new Date());
                        Integer updateFlage = cargoManagementDao.updateCargo(cargo);
                        if (updateFlage==1){
                            return HttpStatus.HTTP_OK;
                        }else {
                            return HttpStatus.HTTP_RESET;
                        }
                    }else {
                        return HttpStatus.HTTP_UNAUTHORIZED;
                    }
                }else {
                    return HttpStatus.HTTP_FORBIDDEN;
                }
            }else {
                return HttpStatus.HTTP_NO_CONTENT;
            }
        }
        return HttpStatus.HTTP_FORBIDDEN;
    }

    @Override
    public Cargo selectOneCargo(Long cargoId) {
        return cargoManagementDao.selectOneCargo(cargoId);
    }

    @Override
    public List<Cargo> selectCargoList() {
        return cargoManagementDao.selectCargoList();
    }
}
