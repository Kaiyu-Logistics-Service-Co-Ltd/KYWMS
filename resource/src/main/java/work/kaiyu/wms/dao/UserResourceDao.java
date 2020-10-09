package work.kaiyu.wms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import work.kaiyu.wms.domain.Resource;

import java.util.List;

@Mapper
public interface UserResourceDao {
    Integer createResource(Resource resource);
    Integer updateResource(Resource resource);
    Integer deleteResource(Resource resource);
    Resource selectOneResource(@Param("resourceType") Integer resourceType,@Param("resourceBy") Long resourceBy);
    List<Resource> selectResourceList(@Param("resourceType") Integer resourceType, @Param("resourceBy") Long resourceBy);
}
