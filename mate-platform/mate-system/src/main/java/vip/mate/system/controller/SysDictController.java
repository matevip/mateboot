package vip.mate.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.AllArgsConstructor;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.common.response.Result;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.log.enums.BusinessType;
import vip.mate.core.mybatis.res.PageRes;
import vip.mate.system.req.SysDictReq;
import vip.mate.system.vo.SysDictVO;
import vip.mate.system.service.SysDictService;
import vip.mate.system.entity.SysDict;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import vip.mate.core.common.constant.MateConstant;

import java.util.List;

/**
 * <p>
 * 字典类型 前端控制器
 * </p>
 *
 * @author matevip
 * @since 2023-09-03
 */
@RestController
@RequestMapping(MateConstant.MATE_SYSTEM_PREFIX_URL + "/dict")
@AllArgsConstructor
@ApiSupport(order = 1)
@Tag(name = "字典类型管理")
@Validated
public class SysDictController {

    private final SysDictService sysDictService;

    @GetMapping("/list")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "列表查询")
    @SaCheckPermission("sysDict:list")
    @Log(title = "字典类型分页", businessType = BusinessType.QUERY)
    public Result<List<SysDictVO>> list() {
        return Result.ok(sysDictService.getList(new SysDictReq()));
    }

    @PostMapping("/add")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "新增", description = "权限字符串：sysDict:add")
    @SaCheckPermission("sysDict:add")
    @Log(title = "新增字典类型", businessType = BusinessType.INSERT)
    public Result<String> create(@Valid @RequestBody SysDict entity) {
        boolean flag = sysDictService.createData(entity);
        return flag ? Result.ok("创建成功") : Result.error("创建失败");
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "修改", description = "权限字符串：sysDict:update")
    @SaCheckPermission("sysDict:update")
    @Log(title = "修改字典类型", businessType = BusinessType.UPDATE)
    public Result<String> update(@Valid @RequestBody SysDict entity) {
        boolean flag = sysDictService.updateData(entity);
        return flag ? Result.ok("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除", description = "权限字符串：sysDict:del")
    @SaCheckPermission("sysDict:del")
    @Log(title = "删除字典类型", businessType = BusinessType.DELETE)
    public Result<String> delete(@RequestParam("id") Long id) {
        boolean flag = sysDictService.removeData(id);
        return flag ? Result.ok("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/get")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "单条查询", description = "权限字符串：sysDict:index")
    @Log(title = "字典类型查询", businessType = BusinessType.QUERY)
    public Result<SysDictVO> getOne(@RequestParam("id") Long id) {
        return Result.ok(sysDictService.getData(id));
    }
}

