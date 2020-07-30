package work.kaiyu.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String userCode;
    private String userPassword;
    private String userName;
    private String userMobile;
    private String userEmail;
    private Long companyId;
    private Integer userType;
    private Long createdBy;
    private Date creationDate;
    private Long modifyBy;
    private Date modifyDate;
    private Company company;
    private DataDictionary dataDictionary;

}
