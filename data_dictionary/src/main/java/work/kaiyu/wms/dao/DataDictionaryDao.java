package work.kaiyu.wms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import work.kaiyu.wms.domain.DataDictionary;

import java.util.List;

@Mapper
public interface DataDictionaryDao {

    List<DataDictionary> queryAllDataDictionaryForSimple();
    DataDictionary queryAllDataDictionaryWithRequirement(@Param("typeCode")String typeCode,@Param("valueId")Integer valueId);
}
