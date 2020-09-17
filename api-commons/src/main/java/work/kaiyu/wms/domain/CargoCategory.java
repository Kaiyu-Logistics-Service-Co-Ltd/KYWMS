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
public class CargoCategory {
    private Long cargoCategoryId;
    private String cargoCategoryCode;
    private String cargoCategoryName;
    private Long cargoCategoryParentId;
    private Long cargoCategoryCreatedBy;
    private Date cargoCategoryCreationDate;
}
