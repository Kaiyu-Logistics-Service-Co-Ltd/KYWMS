package org.example.ec.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import io.seata.spring.annotation.GlobalTransactional;
import org.example.ec.dao.CSRServiceDao;
import org.example.ec.entities.Message;
import org.example.ec.entities.ServiceList;
import org.example.ec.entities.User;
import org.example.ec.entities.UserInfo;
import org.example.ec.exception.DBException;
import org.example.ec.service.CSRSService;
import org.example.ec.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @auther cssly
 * @create 2020/6/27 0:22
 */
@Service
public class CSRSServiceImpl implements CSRSService {
    @Resource
    private CSRServiceDao csrServiceDao;

    /**
     * 对用户模块访问的feign接口
     */
    @Resource
    private UserService userService;

    /**
     * 用户开启新客服请求事务
     *
     * @return i=0 则成功 i=Integer.MIN_VALUE 则失败
     */
    @Override
    @GlobalTransactional(name = "ec_group", rollbackFor = Exception.class, timeoutMills = 100000)
    public Integer beginNewService() {
        /**
         * 预留用户模块接口
         * getSession()
         */
        Integer i = Integer.MIN_VALUE;
        try {
            String tableName = "msg_" + UUID.randomUUID().toString(true);

            Long waiterId = csrServiceDao.selectIdleWaiterId(RandomUtil.randomLong(0, 2));//查找空闲客服

//            User user = csrServiceDao.selectStaticKeyUserId(9527L);//找到测试用户9527.代替获取登录Session
            System.out.println("userService Begin " + new Date());
            UserInfo currentUser = userService.getSession().getData();
            System.out.println("userService End " + new Date());
            if (ObjectUtil.isNull(currentUser) || ObjectUtil.isAllEmpty(currentUser)) {
                throw new DBException.NoData("无用户Session");
            }
            ServiceList serviceList = new ServiceList();//填充实体类
            serviceList.setTableName(tableName);
            serviceList.setUserId(currentUser.getUserId());
            serviceList.setWaiterId(waiterId);

            csrServiceDao.releaseWaiterService(currentUser.getServiceId());//释放客服如果存在

            csrServiceDao.updateCSRService(currentUser.getServiceId());//关闭对应服务id的服务,更新状态为0

            csrServiceDao.insertNewService(serviceList);//将新建的信息表名，用户id，空闲客服id插入进服务列表

            csrServiceDao.updateWaiterServiceOperation(waiterId);//更新客服的服务id

            csrServiceDao.updateUserServiceOperation(currentUser.getUserId());//更新用户的服务id

            csrServiceDao.createTableByPrimaryKey(tableName);//新建一张服务信息表

            i = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 结束与上位用户的关联
     *
     * @return
     */
    @Override
    @GlobalTransactional(name = "ec_group", rollbackFor = Exception.class, timeoutMills = 100000)
    public Integer waiterEndService() {
        /**
         * 预留用户模块接口
         * getSession()
         */
        Integer i = Integer.MIN_VALUE;
        try {
            /**
             * 模拟获取Session
             */
//            User waiter = csrServiceDao.selectStaticKeyUserId(waiterId);
            System.out.println("userService Begin " + new Date());
            UserInfo currentUser = userService.getSession().getData();
            System.out.println("userService End " + new Date());
            if (ObjectUtil.isNull(currentUser) || ObjectUtil.isAllEmpty(currentUser)) {
                throw new DBException.NoData("无用户Session");
            }
            /**
             * 将服务列表相关的状态更新为关闭===>0
             */
            csrServiceDao.updateCSRService(currentUser.getServiceId());
            /**
             * 更新自己的服务参数
             * serviceId ==> 0
             * userStatus ==> 1
             */
            csrServiceDao.releaseWaiterService(currentUser.getServiceId());
            i = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 用户自己退出客服
     *
     * @return
     */
    @Override
    @GlobalTransactional(name = "ec_group", rollbackFor = Exception.class, timeoutMills = 100000)
    public Integer userEndService(Long userId) {
        /**
         * 预留用户模块接口
         * getSession()
         */
        Integer i = Integer.MIN_VALUE;
        try {
            /**
             * 模拟获取Session
             */
//            User user = csrServiceDao.selectStaticKeyUserId(userId);
            System.out.println("userService Begin " + new Date());
            UserInfo currentUser = userService.getSession().getData();
            System.out.println("userService End " + new Date());
            if (ObjectUtil.isNull(currentUser) || ObjectUtil.isAllEmpty(currentUser)) {
                throw new DBException.NoData("无用户Session");
            }
            /**
             * 将相关的服务列表状态更新为关闭===>0
             */
            csrServiceDao.updateCSRService(currentUser.getServiceId());
            /**
             * 释放服务客服
             */
            csrServiceDao.releaseWaiterService(currentUser.getServiceId());
            i = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 查询当前对话
     *
     * @return
     */
    @Override
    public List<Message> queryDialogue() {
        /**
         * 预留用户模块接口
         * getSession()
         */
        List<Message> messageList = new ArrayList<>();
        try {
            /**
             * 模拟获取Session
             */
//            User user = csrServiceDao.selectStaticKeyUserId(8L);
            System.out.println("userService Begin " + new Date());
            UserInfo currentUser = userService.getSession().getData();
            System.out.println("userService End " + new Date());
            if (ObjectUtil.isNull(currentUser) || ObjectUtil.isAllEmpty(currentUser)) {
                throw new DBException.NoData("无用户Session");
            }
            /**
             * 查询活动的信息表名
             */
            String msgTable = csrServiceDao.selectMsgTable(currentUser.getServiceId());
            if (msgTable == null) {
                throw new DBException.NoData("没有找到活动的信息表");
            }
            /**
             * 查询对话内容
             */
            messageList = csrServiceDao.selectMsg(msgTable);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageList;
    }

    /**
     * 发送对话
     *
     * @param msg
     * @return
     */
    @Override
    public Integer sendMsg(String msg) {
        /**
         * 预留用户模块接口
         * getSession()
         */
        Integer i = Integer.MIN_VALUE;
        try {
            /**
             * 模拟获取Session
             */
//            User user = csrServiceDao.selectStaticKeyUserId(8L);
            System.out.println("userService Begin " + new Date());
            UserInfo currentUser = userService.getSession().getData();
            System.out.println("userService End " + new Date());
            if (ObjectUtil.isNull(currentUser) || ObjectUtil.isAllEmpty(currentUser)) {
                throw new DBException.NoData("无用户Session");
            }
            /**
             * 查询信息表名
             */
            String msgTable = csrServiceDao.selectMsgTable(currentUser.getServiceId());
            if (ObjectUtil.isEmpty(msgTable) || ObjectUtil.isNull(msgTable)) {
                throw new DBException.NoData("没有找到活动的信息表");
            }
            Long userId = currentUser.getUserId();
            /**
             * 组装并发送信息
             */
            Map<String, Object> params = new HashMap<>();
            params.put("msgTable", msgTable);
            params.put("msg", new Message(msg, userId));
            csrServiceDao.sendAMsgToDialog(params);
            i = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根据表名进行删除==>预留事务处理
     *
     * @param tableName
     * @return
     */
    @Override
    public Integer dropTableByPrimaryKey(String tableName) {
        /**
         * 预留修改用户接口
         */
        Integer i = Integer.MIN_VALUE;
        try {
            i = csrServiceDao.dropTableByPrimaryKey(tableName);
            //i = 0
        } catch (Exception e) {

        }
        return i;
        //return i ===>0;
    }
}
