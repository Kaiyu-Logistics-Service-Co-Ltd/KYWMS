package work.kaiyu.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    private Long userRoleId;
    private String userRoleName;
    private Long userRoleAuthority;
    private Long createdBy;
    private Date creationDate;
    private Long modifyBy;
    private Date modifyDate;
}
