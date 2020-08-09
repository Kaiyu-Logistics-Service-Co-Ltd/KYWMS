package work.kaiyu.wms.service.impl;

import cn.hutool.core.lang.UUID;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import work.kaiyu.wms.dao.AuthorityDao;
import work.kaiyu.wms.dao.UserRoleDao;
import work.kaiyu.wms.domain.*;
import work.kaiyu.wms.service.RoleWithAuthorityService;
import work.kaiyu.wms.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class RoleWithAuthorityServiceImpl implements RoleWithAuthorityService {

    @Resource
    private AuthorityDao authorityDao;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private UserService userService;

    /**
     * 查询当前用户的权限与角色
     * @return
     */
    @Override
    public RoleAndAuthority selectCurrentUserRAByPrimaryKey() {
        try {
            log.warn("===============================>开始");
            log.info("===============================>获取当前用户Session:BEGIN");
            CommonResult<User> userCommonResult = userService.getSession();
            if (userCommonResult.getCode()==200){
                log.info("===============================>获取当前用户Session:SUCCESS");
                RoleAndAuthority roleAndAuthority = new RoleAndAuthority();
                User currentUser = userCommonResult.getData();
                UserRole currentUserUserRole = currentUser.getUserRole();

                roleAndAuthority.setAuthority(
                        authorityDao.selectByPrimaryKey(
                                currentUserUserRole.getUserRoleAuthority()));

                roleAndAuthority.setUserRole(
                        userRoleDao.selectByPrimaryKey(
                                currentUser.getUserRoleId()));

                return roleAndAuthority;
            }else {
                log.error("================================<获取当前用户Session:FAIL");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("================================<服务器错误");
            return null;
        }
    }

    /**
     * 查询简略用户角色列表
     * @return
     */
    @Override
    public List<UserRole> selectSimpleRoles() {
        try {
            log.warn("===============================>开始");
            log.info("===============================>获取当前用户Session:BEGIN");
            CommonResult<User> userCommonResult = userService.getSession();
            if (userCommonResult.getCode()==200){
                log.info("===============================>获取当前用户Session:SUCCESS");
                /**
                RoleAndAuthority roleAndAuthority = new RoleAndAuthority();
                User currentUser = userCommonResult.getData();
                UserRole currentUserUserRole = currentUser.getUserRole();
                roleAndAuthority.setAuthority(
                        authorityDao.selectByPrimaryKey(
                                currentUserUserRole.getUserRoleAuthority()));

                roleAndAuthority.setUserRole(
                        userRoleDao.selectByPrimaryKey(
                                currentUser.getUserRoleId()));
                 **/
                User currentUser = userCommonResult.getData();
                if (currentUser!=null){
                    return userRoleDao.selectSimpleRoles(currentUser.getUserId());
                }else {
                    log.error("================================<获取当前用户:FAIL");
                }
            }else {
                log.error("================================<获取当前用户Session:FAIL");
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            log.error("================================<服务器错误");
            return null;
        }
    }

    /**
     * DepartmentServiceImpl
     * @return
     */
    @Override
    public List<Department> selectSimpleDepartments() {
        return userRoleDao.selectSimpleDepartments();
    }

    /**
     * 创建角色(权限)
     * @param roleAndAuthority
     * @return
     */
    @Override
    @GlobalTransactional
    public Integer insertRoleAndAuthority(RoleAndAuthority roleAndAuthority) {
        log.warn("===============================>开始");
        log.info("===============================>获取当前用户Session:BEGIN");
        CommonResult<User> userCommonResult = userService.getSession();
        /**
         * 判断是否成功拿到UserSession
         */
        if (userCommonResult.getCode()==200){
            log.info("===============================>获取当前用户Session:SUCCESS");
            /**
             * 数据处理
             */
            User currentUser = userCommonResult.getData();
            log.info("===============================>当前用户:"+currentUser.toString());
            UserRole currentUserRole = currentUser.getUserRole();
            log.info("===============================>获取当前用户权限:BEGIN");
            Authority currentAuthority = authorityDao.selectByPrimaryKey(currentUserRole.getUserRoleAuthority());
            /**
             * 判断是否有对应权限
             */
            if (currentAuthority!=null||currentAuthority.getAuthorityId()!=0){
                /**
                 * 判断是否有修改权限
                 */
                if (currentAuthority.getInsertAuthority()==true
                        &&currentAuthority.getUpdateAuthority()==true
                        &&currentAuthority.getDeleteAuthority()==true){
                    /**
                     * 数据处理
                     */
                    Authority authority = roleAndAuthority.getAuthority();
                    /**
                     * 默认将权限增删改取消
                     */
                    authority.setInsertAuthority(false);
                    authority.setInsertAuthority(false);
                    authority.setDeleteAuthority(false);
                    UserRole userRole = roleAndAuthority.getUserRole();
                    /**
                     * 生成UUID
                     */
                    final String _UUID = UUID.randomUUID().toString(true).toUpperCase();
                    authority.setAuthorityCode(_UUID);
                    /**
                     * 插入权限
                     */
                    authorityDao.insertSelective(authority);
                    /**
                     * 获取权限ID
                     */
                    Long authorityId = authorityDao.selectByAuthorityCode(_UUID);
                    /**
                     * 生成角色
                     */
                    userRole.setCreatedBy(currentUser.getUserId());
                    userRole.setUserRoleAuthority(authorityId);
                    userRoleDao.insertSelective(userRole);
                    return 200;
                }else{
                    log.error("================================<用户权限不足:FAIL");
                    return 300;}
            }else{
                log.error("================================<获取当前用户权限:FAIL");
                return 400;}
        }else{
            log.error("================================<获取当前用户Session:FAIL");
            return 403;}
    }

    @Override
    public Integer deleteRoleAndAuthorityByPrimaryKey(Long authorityId) {
        return null;
    }

    @Override
    public Integer updateRoleAndAuthorityByPrimaryKeySelective(Authority authority) {
        return null;
    }

}
