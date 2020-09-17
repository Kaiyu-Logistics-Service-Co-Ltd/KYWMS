package work.kaiyu.wms.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import work.kaiyu.wms.dao.UserDao;
import work.kaiyu.wms.domain.User;
import work.kaiyu.wms.service.CompanyService;
import work.kaiyu.wms.service.UserService;
import work.kaiyu.wms.utils.AESEncrypt;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Resource
    private CompanyService companyService;

    @Override
    public List<User> queryAllUser(Integer queryType) {
        if(queryType==1){
            return userDao.queryAllUserForSimple();
        }else if (queryType==2){
            return userDao.queryAllUserForDetail();
        }else {
            return null;
        }
    }

    @Override
    public User checkUserLogin(String record, String userPassword, Integer loginType) {
        if(loginType==1){
            return userDao.checkUserLoginByUserCode(record,AESEncrypt.AESEncode(userPassword));
        }else if (loginType==2){
            return null;
        }else if (loginType==3){
            return null;
        }else {
            return null;
        }
    }

    @Override
    public Integer checkIfTheUserCodeExists(String userCode) {
        Long userId = userDao.checkIfTheUserCodeExists(userCode);
        if (userId==null){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    @GlobalTransactional
    public Integer addUser(User addUser, User currentUser) {
        try{
            Integer addFlag = 0;
            if (addUser.getUserRoleId()==0){
                addFlag=401;
            }else if (checkIfTheUserCodeExists(addUser.getUserCode())==0){
                addUser.setCreatedBy(currentUser.getUserId());
                addUser.setUserPassword(AESEncrypt.AESEncode(addUser.getUserPassword()));
                /**
                 * 判断加密是否成功
                 */
                if (addUser.getUserPassword().equals("")||addUser.getUserPassword()==null){
                    addFlag=0;
                }else {
                    addFlag = userDao.insertUser(addUser);
                }
            }else {
                addFlag=402;
            }
            return addFlag;
        }catch (Exception e){
            e.printStackTrace();
            return 500;
        }
    }

    @Override
    public Integer updateUserInfo(User updateUser,User currentUser) {
        try{
            Integer updateFlag = 0;
            /**
             * 只有修改用户昵称的方式。用一个只有userName字段值的对象操作
             */
            User user = new User();
            user.setUserId(currentUser.getUserId());
            user.setUserName(updateUser.getUserName());
            user.setUserMobile(updateUser.getUserMobile());
            updateFlag = userDao.updateUserInfo(user);
            return updateFlag;
        }catch (Exception e){
            e.printStackTrace();
            return 500;
        }
    }

}
