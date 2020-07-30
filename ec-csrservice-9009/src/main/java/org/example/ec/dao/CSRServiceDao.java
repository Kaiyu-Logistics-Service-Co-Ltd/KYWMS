package org.example.ec.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.ec.entities.Message;
import org.example.ec.entities.ServiceList;
import org.example.ec.entities.User;

import java.util.List;
import java.util.Map;

/**
 * @auther cssly
 * @create 2020/6/27 0:23
 */
@Mapper
public interface CSRServiceDao {
    Integer createTableByPrimaryKey(@Param("tableName")String tableName);

    Integer dropTableByPrimaryKey(@Param("tableName")String tableName);

    Integer updateCSRService(@Param("serviceId")Long serviceId);

    Integer insertNewService(ServiceList serviceList);

    Integer releaseWaiterService(@Param("serviceId")Long serviceId);
    Long selectIdleWaiterId(@Param("orderBy")Long orderBy);
    Integer updateWaiterServiceOperation(@Param("waiterId")Long waiterId);
    Integer updateUserServiceOperation(@Param("userId")Long userId);
    User selectStaticKeyUserId(@Param("userId")Long userId);

    String selectMsgTable(@Param("serviceId")Long serviceId);
    List<Message> selectMsg(@Param("tableName") String tableName);
    Integer sendAMsgToDialog(@Param("params") Map<String,Object> map);
}
