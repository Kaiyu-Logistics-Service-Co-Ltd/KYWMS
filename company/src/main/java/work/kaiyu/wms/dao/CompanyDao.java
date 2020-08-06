package work.kaiyu.wms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import work.kaiyu.wms.domain.Company;

import java.util.List;

@Mapper
public interface CompanyDao {
    List<Company> queryAllCompanyForSimple();
    Company queryCompanyWithPrimaryKey(@Param("companyId")Long companyId);
    Integer insertCompany(Company company);
}
