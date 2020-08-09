package work.kaiyu.wms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import work.kaiyu.wms.domain.Department;
import work.kaiyu.wms.domain.UserRole;

import java.util.List;

@Mapper
public interface UserRoleDao {

    Integer insertSelective(UserRole record);

    Integer deleteByPrimaryKey(Long userRoleId);

    Integer updateByPrimaryKeySelective(UserRole record);

    UserRole selectByPrimaryKey(Long userRoleId);

    List<UserRole> selectSimpleRoles(@Param("createdBy") Long createdBy);

    /**
     * DepartmentDao
     * @return
     */
    List<Department> selectSimpleDepartments();
}