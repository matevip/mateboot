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
import vip.mate.system.req.SysDeptReq;
import vip.mate.system.vo.SysDeptVO;
import vip.mate.system.service.SysDeptService;
import vip.mate.system.entity.SysDept;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import vip.mate.core.common.constant.MateConstant;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author matevip
 * @since 2023-08-24
 */
@RestController
@RequestMapping(MateConstant.MATE_SYSTEM_PREFIX_URL + "/dept")
@AllArgsConstructor
@ApiSupport(author = "matevip", order = 1)
@Tag(description = "sysDept", name = "部门管理")
@Validated
public class SysDeptController {

    private final SysDeptService sysDeptService;

    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "列表查询",description = "权限字符串：sysDept:page")
//    @SaCheckPermission("sysDept:page")
    public Result<PageRes<SysDeptVO>> queryPage(SysDeptReq req) {
        return Result.ok(sysDeptService.queryPage(req));
    }

    @PostMapping("/add")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "新增",description = "权限字符串：sysDept:add")
//    @SaCheckPermission("sysDept:add")
    public Result<String> create(@Valid @RequestBody SysDept entity){
        boolean flag = sysDeptService.createData(entity);
        return flag ? Result.ok("创建成功") : Result.error("创建失败");
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "修改",description = "权限字符串：sysDept:update")
//    @SaCheckPermission("sysDept:update")
    public Result<String> update(@Valid @RequestBody SysDept entity){
        boolean flag = sysDeptService.updateData(entity);
        return flag ? Result.ok("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除",description = "权限字符串：sysDept:del")
//    @SaCheckPermission("sysDept:del")
    public Result<String> delete(@RequestParam("id") Long id){
        boolean flag = sysDeptService.removeData(id);
        return flag ? Result.ok("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/get")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "单条查询",description = "权限字符串：sysDept:index")
//    @SaCheckPermission("sysDept:index")
    public Result<SysDeptVO> getOne(@RequestParam("id") Long id){
        return Result.ok(sysDeptService.getData(id));
    }
}

