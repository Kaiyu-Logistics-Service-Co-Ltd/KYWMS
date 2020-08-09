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

    public Authority(Long authorityId, Boolean queryWaybill, Boolean insertWaybill, Boolean deleteWaybill, Boolean updateWaybill, Boolean queryAllocateVehicles, Boolean insertAllocateVehicles, Boolean deleteAllocateVehicles, Boolean updateAllocateVehicles, Boolean queryFinance, Boolean insertFinance, Boolean deleteFinance, Boolean updateFinance, Boolean queryCustomer, Boolean insertCustomer, Boolean deleteCustomer, Boolean updateCustomer, Boolean queryAuthority, Boolean insertAuthority, Boolean deleteAuthority, Boolean updateAuthority, Boolean queryUser, Boolean insertUser, Boolean deleteUser, Boolean updateUser, Boolean queryNetWaybill, Boolean insertNetWaybill, Boolean deleteNetWaybill, Boolean updateNetWaybill) {
        this(authorityId,null,queryWaybill,insertWaybill,deleteWaybill,updateWaybill,queryAllocateVehicles,insertAllocateVehicles,deleteAllocateVehicles,updateAllocateVehicles,queryFinance,insertFinance,deleteFinance,updateFinance,queryCustomer,insertCustomer,deleteCustomer,updateCustomer,queryAuthority,insertAuthority,deleteAuthority,updateAuthority,queryUser,insertUser,deleteUser,updateUser,queryNetWaybill,insertNetWaybill,deleteNetWaybill,updateNetWaybill);
    }

}