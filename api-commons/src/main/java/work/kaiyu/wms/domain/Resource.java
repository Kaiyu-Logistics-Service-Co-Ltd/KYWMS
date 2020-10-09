package work.kaiyu.wms.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    private Long resourceId;
    private Integer resourceType;
    private String resourcePath;
    private String resourceRealPath;
    private Long resourceBy;

    public Resource(Integer resourceType, String resourcePath, String resourceRealPath, Long resourceBy) {
        this(null,resourceType,resourcePath,resourceRealPath,resourceBy);
    }
}
