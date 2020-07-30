import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import org.example.ec.dao.CSRServiceDao;
import org.example.ec.entities.User;
import org.example.ec.service.CSRSService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @auther cssly
 * @create 2020/6/27 15:30
 */
public class TestTableManage {

    @Test
    public void testRandomUtil(){
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtil.randomLong(0, 2));
        }
    }
    @Test
    public void testHutool(){
        User user = new User();
        User user1 = null;
        if (ObjectUtil.isEmpty(user1)){
            System.out.println("NULL2");
        }
    }
}
