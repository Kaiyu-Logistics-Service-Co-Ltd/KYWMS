package work.kaiyu.wms.service.impl;

import org.springframework.stereotype.Service;
import work.kaiyu.wms.dao.DataDictionaryDao;
import work.kaiyu.wms.domain.DataDictionary;
import work.kaiyu.wms.service.DataDictionaryService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Resource
    private DataDictionaryDao dataDictionaryDao;
    @Override
    public List<DataDictionary> queryAllDataDictionaryForSimple() {
        return dataDictionaryDao.queryAllDataDictionaryForSimple();
    }

    @Override
    public DataDictionary queryAllDataDictionaryWithRequirement(String typeCode, Integer valueId) {
        return dataDictionaryDao.queryAllDataDictionaryWithRequirement(typeCode,valueId);
    }
}
