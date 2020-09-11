package work.kaiyu.wms.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String userCode;
    private String userPassword;
    private String userName;
    private String userMobile;
    private String userEmail;
    private String resourceCode;
    private Long departmentId;
    private Long userRoleId;
    private Long createdBy;
    private Date creationDate;
    private Long modifyBy;
    private Date modifyDate;
    private Department department;
    private UserRole userRole;

}
