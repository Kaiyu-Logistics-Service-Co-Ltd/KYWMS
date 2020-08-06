package work.kaiyu.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private Long departmentId;
    private String departmentName;
    private Long departmentManager;

}
