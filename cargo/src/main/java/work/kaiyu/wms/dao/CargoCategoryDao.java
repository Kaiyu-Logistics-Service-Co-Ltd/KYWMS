package work.kaiyu.wms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import work.kaiyu.wms.domain.CargoCategory;

import java.util.List;

@Mapper
public interface CargoCategoryDao {

    Integer addCargoCategory(CargoCategory cargoCategory);
    Integer deleteCargoCategory(@Param("cargoCategoryId")Long cargoCategoryId);
    Integer updateCargoCategory(CargoCategory cargoCategory);
    List<CargoCategory> getCargoCategoryListByParentId(@Param("cargoCategoryParentId")Long cargoCategoryParentId);
}
