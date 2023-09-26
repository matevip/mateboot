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
import vip.mate.system.req.SysDictItemReq;
import vip.mate.system.vo.SysDictItemVO;
import vip.mate.system.service.SysDictItemService;
import vip.mate.system.entity.SysDictItem;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import vip.mate.core.common.constant.MateConstant;

/**
 * <p>
 * 字典项 前端控制器
 * </p>
 *
 * @author matevip
 * @since 2023-09-03
 */
@RestController
@RequestMapping(MateConstant.MATE_SYSTEM_PREFIX_URL + "/dictItem")
@AllArgsConstructor
@ApiSupport(order = 1)
@Tag(name = "字典项管理")
@Validated
public class SysDictItemController {

    private final SysDictItemService sysDictItemService;

    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "列表查询")
    @SaCheckPermission("sysDict:list")
    @Log(title = "字典项分页", businessType = BusinessType.QUERY)
    public Result<PageRes<SysDictItemVO>> queryPage(SysDictItemReq req) {
        return Result.ok(sysDictItemService.queryPage(req));
    }

    @PostMapping("/add")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "新增")
    @SaCheckPermission("sysDict:add")
    @Log(title = "新增字典项", businessType = BusinessType.INSERT)
    public Result<String> create(@Valid @RequestBody SysDictItem entity){
        boolean flag = sysDictItemService.createData(entity);
        return flag ? Result.ok("创建成功") : Result.error("创建失败");
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "修改")
    @SaCheckPermission("sysDict:update")
    @Log(title = "修改字典项", businessType = BusinessType.UPDATE)
    public Result<String> update(@Valid @RequestBody SysDictItem entity){
        boolean flag = sysDictItemService.updateData(entity);
        return flag ? Result.ok("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除")
    @SaCheckPermission("sysDict:del")
    @Log(title = "删除字典项", businessType = BusinessType.DELETE)
    public Result<String> delete(@RequestParam("id") Long id){
        boolean flag = sysDictItemService.removeData(id);
        return flag ? Result.ok("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/get")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "单条查询")
    @Log(title = "字典项查询", businessType = BusinessType.QUERY)
    public Result<SysDictItemVO> getOne(@RequestParam("id") Long id){
        return Result.ok(sysDictItemService.getData(id));
    }
}

