package work.kaiyu.wms.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import work.kaiyu.wms.dao.CompanyDao;
import work.kaiyu.wms.domain.Company;
import work.kaiyu.wms.service.CompanyService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyDao companyDao;

    @Override
    public List<Company> queryAllCompany() {
        return companyDao.queryAllCompanyForSimple();
    }

    @Override
    public Company queryCompanyWithPrimaryKey(Long companyId) {
        if (companyId!=null){
            return companyDao.queryCompanyWithPrimaryKey(companyId);
        }else{
            return null;
        }
    }

    @Override
    public Integer insertCompany(Company company) {
        return companyDao.insertCompany(company);
    }

}
