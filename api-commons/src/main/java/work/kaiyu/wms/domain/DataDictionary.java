package work.kaiyu.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataDictionary {
    private Long dataId;
    private String typeCode;
    private String typeName;
    private Integer valueId;
    private String valueName;
    private Long createdBy;
    private Date creationDate;
    private Long modifyBy;
    private Date modifyDate;
}
