package work.kaiyu.wms.service;

import org.apache.ibatis.annotations.Param;
import work.kaiyu.wms.domain.DataDictionary;

import java.util.List;

public interface DataDictionaryService {

    List<DataDictionary> queryAllDataDictionaryForSimple();
    DataDictionary queryAllDataDictionaryWithRequirement(String typeCode,Integer valueId);

}
