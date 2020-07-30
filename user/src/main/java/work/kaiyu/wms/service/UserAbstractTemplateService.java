package work.kaiyu.wms.service;

import org.springframework.stereotype.Service;
import work.kaiyu.wms.domain.User;

import java.util.List;

public abstract class UserAbstractTemplateService implements UserService{
    @Override
    public List<User> queryAllUser(Integer queryType) {
        if(queryType==1){
            return queryUserSimple();
        }else if (queryType==2){
            return queryUserDetail();
        }else {
            return null;
        }
    }
    public abstract List<User> queryUserSimple();

    public abstract List<User> queryUserDetail();

}
