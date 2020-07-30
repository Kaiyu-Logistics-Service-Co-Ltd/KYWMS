package work.kaiyu.wms.service.impl;

import org.springframework.stereotype.Service;
import work.kaiyu.wms.dao.UserDao;
import work.kaiyu.wms.domain.User;
import work.kaiyu.wms.service.UserAbstractTemplateService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends UserAbstractTemplateService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> queryUserSimple() {
        return userDao.queryUserForSimple();
    }

    @Override
    public List<User> queryUserDetail() {
        return userDao.queryAllUserForDetail();
    }
}
