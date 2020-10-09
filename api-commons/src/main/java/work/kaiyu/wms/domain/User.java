package work.kaiyu.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;
    private String userCode;
    private String userPassword;
    private String userName;
    private String userMobile;
    private String userEmail;
    private Long departmentId;
    private Long userRoleId;
    private Long resourceId;
    private Long createdBy;
    private Date creationDate;
    private Long modifyBy;
    private Date modifyDate;
    private Department department;
    private UserRole userRole;

}
