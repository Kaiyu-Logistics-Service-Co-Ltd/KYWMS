package work.kaiyu.wms.service;

import work.kaiyu.wms.domain.CargoCategory;

import java.util.List;

public interface CargoCategoryService {

    Integer addCargoCategory(CargoCategory cargoCategory);
    Integer deleteCargoCategory(Long cargoCategoryId);
    Integer updateCargoCategory(CargoCategory cargoCategory);
    List<CargoCategory> getCargoCategoryListByParentId(Long cargoCategoryParentId);

}
