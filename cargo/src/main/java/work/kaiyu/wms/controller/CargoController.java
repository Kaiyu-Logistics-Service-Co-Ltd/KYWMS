package work.kaiyu.wms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import work.kaiyu.wms.domain.CargoCategory;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.service.CargoCategoryService;
import work.kaiyu.wms.service.CargoManagementService;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/cargo")
public class CargoController {

    @Resource
    private CargoCategoryService cargoCategoryService;

    @Resource
    private CargoManagementService cargoManagementService;

    /**
     * 获取分类列表
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
    @PostMapping("/addCategory")
    public CommonResult addCategory(@RequestBody CargoCategory cargoCategory) {
        try{
            Integer addFlag = cargoCategoryService.addCargoCategory(cargoCategory);
            if (addFlag==200){
                return new CommonResult(200,"添加分类成功");
            }else if (addFlag==205){
                return new CommonResult(addFlag,"添加分类失败");
            }else if (addFlag==300){
                return new CommonResult(addFlag,"用户权限不足");
            }else if (addFlag==400){
                return new CommonResult(addFlag,"获取当前用户权限失败");
            }else if (addFlag==404){
                return new CommonResult(addFlag,"获取当前用户权限失败");
            }else if (addFlag==403){
                return new CommonResult(addFlag,"请登录");
            }else {
                return new CommonResult(504,"未知问题");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error");
        }
    }
    @PostMapping("/deleteCategory")
    public CommonResult deleteCategory(@RequestParam("cargoCategoryId") Long cargoCategoryId) {
        try{
            Integer deleteFlag = cargoCategoryService.deleteCargoCategory(cargoCategoryId);
            if (deleteFlag==200){
                return new CommonResult(200,"删除分类成功");
            }else if (deleteFlag==205){
                return new CommonResult(deleteFlag,"删除分类失败");
            }else if (deleteFlag==300){
                return new CommonResult(deleteFlag,"用户权限不足");
            }else if (deleteFlag==400){
                return new CommonResult(deleteFlag,"获取当前用户权限失败");
            }else if (deleteFlag==404){
                return new CommonResult(deleteFlag,"获取当前用户权限失败");
            }else if (deleteFlag==403){
                return new CommonResult(deleteFlag,"请登录");
            }else {
                return new CommonResult(504,"未知问题");
            }
        }catch (Exception e){
            return new CommonResult(500,"Error");
        }
    }
    @PostMapping("/updateCategory")
    public CommonResult updateCategory(@RequestBody CargoCategory cargoCategory) {
        try{
            Integer updateFlag = cargoCategoryService.updateCargoCategory(cargoCategory);
            if (updateFlag==200){
                return new CommonResult(200,"修改分类成功");
            }else if (updateFlag==205){
                return new CommonResult(updateFlag,"修改分类失败");
            }else if (updateFlag==300){
                return new CommonResult(updateFlag,"用户权限不足");
            }else if (updateFlag==400){
                return new CommonResult(updateFlag,"获取当前用户权限失败");
            }else if (updateFlag==404){
                return new CommonResult(updateFlag,"获取当前用户权限失败");
            }else if (updateFlag==403){
                return new CommonResult(updateFlag,"请登录");
            }else {
                return new CommonResult(504,"未知问题");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResult(500,"Error");
        }
    }
}