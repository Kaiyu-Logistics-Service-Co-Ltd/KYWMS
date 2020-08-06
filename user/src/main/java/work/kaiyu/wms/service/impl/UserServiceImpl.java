package work.kaiyu.wms.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import work.kaiyu.wms.dao.UserDao;
import work.kaiyu.wms.domain.User;
import work.kaiyu.wms.service.CompanyService;
import work.kaiyu.wms.service.UserService;
import work.kaiyu.wms.utils.AESEncrypt;

import javax.annotation.Resource;
import java.sql.SQLException;
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
    @GlobalTransactional
    public Integer regUser(User user,Long createBy) {
        try{
            Integer regFlag = 0;
            user.setUserPassword(AESEncrypt.AESEncode(user.getUserPassword()));
            /**
             * 判断加密是否成功
             */
            if (user.getUserPassword().equals("")||user.getUserPassword()==null){
                return regFlag;
            }else {
                regFlag = userDao.insertUser(user);
                return regFlag;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

}
