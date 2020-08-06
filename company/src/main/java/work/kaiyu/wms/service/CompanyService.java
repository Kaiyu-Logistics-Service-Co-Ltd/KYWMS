package work.kaiyu.wms.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import work.kaiyu.wms.domain.Company;

import java.util.List;

public interface CompanyService {
    List<Company> queryAllCompany();
    Company queryCompanyWithPrimaryKey(Long companyId);
    Integer insertCompany(Company company);
}
