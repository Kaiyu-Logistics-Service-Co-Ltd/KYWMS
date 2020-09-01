package work.kaiyu.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoCategory {
    private Long cargoCategoryId;
    private String cargoCategoryCode;
    private String cargoCategoryName;
    private Long cargoCategoryParentId;
    private Long cargoCategoryCreatedBy;
    private Date cargoCategoryCreationDate;
}
