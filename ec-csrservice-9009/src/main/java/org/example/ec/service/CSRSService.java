package org.example.ec.service;

import org.apache.ibatis.annotations.Param;
import org.example.ec.entities.Message;

import java.util.List;


/**
 * @auther cssly
 * @create 2020/6/27 0:22
 */
public interface CSRSService {
    Integer dropTableByPrimaryKey(@Param("tableName")String tableName);
    Integer beginNewService();
    Integer waiterEndService();
    Integer userEndService(Long userId);
    List<Message> queryDialogue();
    Integer sendMsg(String msg);
}
