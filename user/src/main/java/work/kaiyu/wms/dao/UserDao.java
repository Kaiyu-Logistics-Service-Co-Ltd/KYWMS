package work.kaiyu.wms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import work.kaiyu.wms.domain.User;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> queryAllUserForSimple();
    List<User> queryAllUserForDetail();
    List<User> queryAllUserForSimpleDetail();

    /**
     * 登录
     * @param userCode
     * @param userPassword
     * @return
     */
    User checkUserLoginByUserCode(@Param("userCode") String userCode,@Param("userPassword") String userPassword);

    /**
     * 判断用户是否存在
     * @param userCode
     * @return
     */
    Long checkUserIsExisted(@Param("userCode")String userCode);

    /**
     * 插入用户
     * @param user
     * @return
     */
    Integer insertUser(User user);
}
