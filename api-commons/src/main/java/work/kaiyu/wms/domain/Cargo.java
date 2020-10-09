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
public class Cargo {
    private Long cargoId;
    private String cargoCode;
    private String cargoName;
    private Long cargoCategoryId;
    private String cargoDescription;
    private String cargoDetails;
    private Long createdBy;
    private Date creationDate;
    private Long modifyBy;
    private Date modifyDate;
}
