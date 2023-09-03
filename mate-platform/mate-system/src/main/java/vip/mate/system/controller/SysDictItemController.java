package vip.mate.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.AllArgsConstructor;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.common.response.Result;
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
@ApiSupport(author = "matevip", order = 1)
@Tag(description = "sysDictItem", name = "字典项管理")
@Validated
public class SysDictItemController {

    private final SysDictItemService sysDictItemService;

    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "列表查询",description = "权限字符串：sysDictItem:page")
    @SaCheckPermission("sysDictItem:page")
    public Result<PageRes<SysDictItemVO>> queryPage(SysDictItemReq req) {
        return Result.ok(sysDictItemService.queryPage(req));
    }

    @PostMapping("/add")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "新增",description = "权限字符串：sysDictItem:add")
    @SaCheckPermission("sysDictItem:add")
    public Result<String> create(@Valid @RequestBody SysDictItem entity){
        boolean flag = sysDictItemService.createData(entity);
        return flag ? Result.ok("创建成功") : Result.error("创建失败");
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "修改",description = "权限字符串：sysDictItem:update")
    @SaCheckPermission("sysDictItem:update")
    public Result<String> update(@Valid @RequestBody SysDictItem entity){
        boolean flag = sysDictItemService.updateData(entity);
        return flag ? Result.ok("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除",description = "权限字符串：sysDictItem:del")
    @SaCheckPermission("sysDictItem:del")
    public Result<String> delete(@RequestParam("id") Long id){
        boolean flag = sysDictItemService.removeData(id);
        return flag ? Result.ok("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/get")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "单条查询",description = "权限字符串：sysDictItem:index")
    @SaCheckPermission("sysDictItem:index")
    public Result<SysDictItemVO> getOne(@RequestParam("id") Long id){
        return Result.ok(sysDictItemService.getData(id));
    }
}

