package work.kaiyu.wms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sun.font.TrueTypeFont;
import work.kaiyu.wms.domain.Cargo;
import work.kaiyu.wms.domain.CargoCategory;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.service.CargoCategoryService;
import work.kaiyu.wms.service.CargoManagementService;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/cargo")
public class CargoController {

    @Resource
    private CargoCategoryService cargoCategoryService;
    @Resource
    private CargoManagementService cargoManagementService;

    /**
     * 返回编码抽取
     * @param type 是否自定义消息
     * @param flag 错误编码
     * @param spliceMsg 拼接字符串
     * @param data 数据 nullable
     * @return
     */
    private CommonResult returnType(Boolean type, Integer flag, String spliceMsg, Object data){
        if (type){
            if (data==null){
                return new CommonResult(flag,spliceMsg);
            }else {
                return new CommonResult(flag,spliceMsg,data);
            }
        }else {
            if (flag==200){
                return new CommonResult(flag,spliceMsg+"成功");
            }else if (flag==203){
                return new CommonResult(flag,"未经授权");
            }else if (flag==204){
                return new CommonResult(flag,"服务连接失败");
            }else if (flag==205){
                return new CommonResult(flag,spliceMsg+"失败");
            }else if (flag==401){
                return new CommonResult(flag,"用户权限不足");
            }else if (flag==403){
                return new CommonResult(flag,"服务器禁止请求");
            }else {
                return new CommonResult(flag,"未知问题");
            }
        }
    }
    private CommonResult returnType(Boolean type, Integer flag, String spliceMsg){
        return returnType(type, flag, spliceMsg,null);
    }

    /**
     * 获取分类列表(带分页)
     * @param cargoCategoryParentId
     * @return
     */
    @GetMapping("/getCategoryByParentId")
    public CommonResult getCategoryByParentId(@RequestParam(value = "pn",defaultValue = "1",required = false)Integer pn,
                                              @RequestParam("cargoCategoryParentId") Long cargoCategoryParentId) {
        try{
            PageHelper.startPage(pn,5);
            //使用pageInfo包装查询后的结果,只需要将pageInfo交给页面就行了
            //封装了详细的分页信息，包括有我们查询出来的数据,传入连续显示的页数
            PageInfo page = new PageInfo(cargoCategoryService.getCargoCategoryListByParentId(cargoCategoryParentId),5);
            return new CommonResult(200,"获取分类成功",page);
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error");
        }
    }
    /**
     * 获取一级分类
     * @return
     */
    @GetMapping("/getFirstCategory")
    public CommonResult getFirstCategory() {
        try{
            return new CommonResult(200,"获取分类成功",cargoCategoryService.getCargoCategoryListByParentId(0L));
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error");
        }
    }
    /**
     * 增加分类
     * @param cargoCategory
     * @return
     */
    @PostMapping("/addCategory")
    public CommonResult addCategory(@RequestBody CargoCategory cargoCategory) {
        try{
            cargoCategory.setCargoCategoryId(null);
            Integer addFlag = cargoCategoryService.addCargoCategory(cargoCategory);
            return returnType(false,addFlag,"增加分类");

        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error");
        }
    }
    /**
     * 删除分类
     * @param cargoCategoryId
     * @return
     */
    @PostMapping("/deleteCategory")
    public CommonResult deleteCategory(@RequestParam("cargoCategoryId") Long cargoCategoryId) {
        try{
            Integer deleteFlag = cargoCategoryService.deleteCargoCategory(cargoCategoryId);
            return returnType(false,deleteFlag,"删除分类");
        }catch (Exception e){
            return new CommonResult(500,"Error");
        }
    }
    /**
     * 更新分类
     * @param cargoCategory
     * @return
     */
    @PostMapping("/updateCategory")
    public CommonResult updateCategory(@RequestBody CargoCategory cargoCategory) {
        try{
            Integer updateFlag = cargoCategoryService.updateCargoCategory(cargoCategory);
            return returnType(false,updateFlag,"修改分类");
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error");
        }
    }

    @PostMapping("/addCargo")
    public CommonResult addCargo(@RequestBody Cargo cargo){
        try{
            Integer flag = cargoManagementService.addCargo(cargo);
            return returnType(false,flag,"添加货物信息");
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error");
        }
    }
    @PostMapping("/deleteCargo")
    public CommonResult deleteCargo(@RequestParam(value = "cargoId")Long cargoId){
        try{
            Integer flag = cargoManagementService.deleteCargo(cargoId);
            return returnType(false,flag,"删除货物信息");
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error");
        }
    }
    @PostMapping("/updateCargo")
    public CommonResult updateCargo(@RequestBody Cargo cargo){
        try{
            Integer flag = cargoManagementService.updateCargo(cargo);
            return returnType(false,flag,"修改货物信息");
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error");
        }
    }
    @GetMapping("/selectOneCargo")
    public CommonResult selectOneCargo(@RequestParam(value = "cargoId")Long cargoId){
        try{
            Cargo cargo = cargoManagementService.selectOneCargo(cargoId);
            return returnType(true,200,"查询单个货物信息",cargo);
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error");
        }
    }
    @GetMapping("/selectCargoList")
    public CommonResult selectCargoList(){
        try{
            List<Cargo> cargoList = cargoManagementService.selectCargoList();
            return returnType(true,200,"查询货物信息列表",cargoList);
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error");
        }
    }



}