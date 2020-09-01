package work.kaiyu.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {
    private Long authorityId;

    private String authorityCode;

    private Boolean queryWaybill;
    private Boolean insertWaybill;
    private Boolean deleteWaybill;
    private Boolean updateWaybill;

    private Boolean queryAllocateVehicles;
    private Boolean insertAllocateVehicles;
    private Boolean deleteAllocateVehicles;
    private Boolean updateAllocateVehicles;

    private Boolean queryFinance;
    private Boolean insertFinance;
    private Boolean deleteFinance;
    private Boolean updateFinance;

    private Boolean queryCustomer;
    private Boolean insertCustomer;
    private Boolean deleteCustomer;
    private Boolean updateCustomer;

    private Boolean queryAuthority;
    private Boolean insertAuthority;
    private Boolean deleteAuthority;
    private Boolean updateAuthority;

    private Boolean queryUser;
    private Boolean insertUser;
    private Boolean deleteUser;
    private Boolean updateUser;

    private Boolean queryNetWaybill;
    private Boolean insertNetWaybill;
    private Boolean deleteNetWaybill;
    private Boolean updateNetWaybill;

    private Boolean queryCargoCategory;
    private Boolean insertCargoCategory;
    private Boolean deleteCargoCategory;
    private Boolean updateCargoCategory;

    private Boolean queryCargo;
    private Boolean insertCargo;
    private Boolean deleteCargo;
    private Boolean updateCargo;

}