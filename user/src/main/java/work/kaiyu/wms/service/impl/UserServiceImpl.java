package work.kaiyu.wms.service.impl;

import org.springframework.stereotype.Service;
import work.kaiyu.wms.dao.UserDao;
import work.kaiyu.wms.domain.User;
import work.kaiyu.wms.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> queryAllUser(Integer queryType) {
        if(queryType==1){
            return userDao.queryAllUser();
        }else if (queryType==2){
            return userDao.queryAllUserForDetail();
        }else {
            return null;
        }
    }
}
