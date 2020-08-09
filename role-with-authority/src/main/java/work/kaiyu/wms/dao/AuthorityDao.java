package work.kaiyu.wms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import work.kaiyu.wms.domain.Authority;

@Mapper
public interface AuthorityDao {

    Authority selectByPrimaryKey(@Param("authorityId") Long authorityId);
    Long selectByAuthorityCode(@Param("authorityCode")String authorityCode);
    Integer insertSelective(Authority authority);

    Integer deleteByPrimaryKey(@Param("authorityId") Long authorityId);

    Integer updateByPrimaryKeySelective(Authority authority);
}