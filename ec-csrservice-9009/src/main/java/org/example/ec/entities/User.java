package org.example.ec.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @auther cssly
 * @create 2020/6/26 15:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    private String userCode;
    private String userPassword;
    private String userName;
    private BigDecimal userBalance;
    private String createBy;
    private Date createdDate;
    private String modifyBy;
    private Date modifyDate;
    private Long serviceId;
    private Integer role;
    private Integer userStatus;
    private String userCart;
}
