package work.kaiyu.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAndAuthority {
    private UserRole userRole;
    private Authority authority;
}
