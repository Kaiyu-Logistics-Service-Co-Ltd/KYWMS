package work.kaiyu.wms.service;

import org.springframework.web.multipart.MultipartFile;
import work.kaiyu.wms.domain.Resource;

import java.util.List;

public interface ResourceService {
    Boolean createResource(Integer type);
    Boolean insertResource(Integer type, MultipartFile[] multipartFiles);
    Integer updateResource(Resource resource);
    Integer deleteResource(Resource resource);
    Resource selectOneResource(Integer resourceType,Long resourceBy);
    List<Resource> selectResourceList(Integer resourceType,Long resourceBy);
}
