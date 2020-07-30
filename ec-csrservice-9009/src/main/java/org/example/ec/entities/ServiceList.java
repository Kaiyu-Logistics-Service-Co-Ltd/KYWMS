package org.example.ec.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

/**
 * @auther cssly
 * @create 2020/6/27 14:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceList {
    /**
     * 服务发起编号
     */
    private Long serviceId;
    /**
     * 服务表名
     */
    private String tableName;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 客服id
     */
    private Long waiterId;
    /**
     * 创建时间
     */
    private Date createdDate;
    /**
     * 服务状态
     */
    private Integer serviceStatus;

    public ServiceList(String tableName, Long userId, Long waiterId) {
        this(null,tableName,userId,waiterId,null,null);
    }

}
