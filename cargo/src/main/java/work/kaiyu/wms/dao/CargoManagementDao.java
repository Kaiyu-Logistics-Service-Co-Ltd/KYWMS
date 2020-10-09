package work.kaiyu.wms.dao;

import work.kaiyu.wms.domain.Cargo;

import java.util.List;

public interface CargoManagementDao {
    Integer addCargo(Cargo cargo);
    Integer deleteCargo(Long cargoId);
    Integer updateCargo(Cargo cargo);
    Cargo selectOneCargo(Long cargoId);
    List<Cargo> selectCargoList();
}
