package work.kaiyu.wms.service;

import work.kaiyu.wms.domain.Authority;
import work.kaiyu.wms.domain.Department;
import work.kaiyu.wms.domain.RoleAndAuthority;
import work.kaiyu.wms.domain.UserRole;

import java.util.List;

public interface RoleWithAuthorityService {

    RoleAndAuthority selectCurrentUserRAByPrimaryKey();

    Integer insertRoleAndAuthority(RoleAndAuthority roleAndAuthority);

    Integer deleteRoleAndAuthorityByPrimaryKey(Long authorityId);

    Integer updateRoleAndAuthorityByPrimaryKeySelective(Authority authority);

    List<UserRole> selectSimpleRoles();

    /**
     * DepartmentService
     * @return
     */
    List<Department> selectSimpleDepartments();
}
