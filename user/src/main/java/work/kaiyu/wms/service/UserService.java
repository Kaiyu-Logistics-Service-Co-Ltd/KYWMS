package work.kaiyu.wms.service;

import work.kaiyu.wms.domain.User;

import java.util.List;

public interface UserService {
    /**
     * @param queryType
     * [1,2] 1=>查询简单信息 2=>查询详细信息
     */
    List<User> queryAllUser(Integer queryType);

    User checkUserLogin(String record,String userPassword,Integer loginType);

    Integer addUser(User addUser, User currentUser);
    Integer updateUserInfo(User updateUser,User currentUser);
    Integer checkIfTheUserCodeExists(String userCode);
}
