package org.example.ec.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

/**
 * @auther cssly
 * @create 2020/6/27 14:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    /**
     * 信息id  ==>自增
     */
    private Long msgId;
    /**
     * 信息内容
     */
    private String content;
    /**
     * 发信人
     */
    private Long createdBy;
    /**
     * 创建日期 ==>自增
     */
    private Date createdDate;

    public Message(String content,Long createBy) {
        this(null,content,createBy,null);
    }
}
