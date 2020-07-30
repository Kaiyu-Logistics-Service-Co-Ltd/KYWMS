package work.kaiyu.wms.dao;

import org.apache.ibatis.annotations.Mapper;
import work.kaiyu.wms.domain.User;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> queryUserForSimple();
    List<User> queryAllUserForDetail();
}
