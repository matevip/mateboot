package vip.mate.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
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
import vip.mate.system.req.SysUserReq;
import vip.mate.system.service.SysUserRoleService;
import vip.mate.system.vo.SysUserVO;
import vip.mate.system.service.SysUserService;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import vip.mate.core.common.constant.MateConstant;

import java.util.List;

/**
 * <p>
 * 用户管理 前端控制器
 * </p>
 *
 * @author matevip
 * @since 2023-08-21
 */
@RestController
@RequestMapping(MateConstant.MATE_SYSTEM_PREFIX_URL + "/user")
@AllArgsConstructor
@ApiSupport(order = 1)
@Tag(name = "用户管理")
@Validated
public class SysUserController {

    private final SysUserService sysUserService;
    private final SysUserRoleService sysUserRoleService;

    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "列表查询", description = "权限字符串：sysUser:page")
    @SaCheckPermission("sysUser:list")
    @Log(title = "列表查询", businessType = BusinessType.QUERY)
    public Result<PageRes<SysUserVO>> queryPage(SysUserReq req) {
        return Result.ok(sysUserService.queryPage(req));
    }

    @PostMapping("/add")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "新增", description = "权限字符串：sysUser:add")
    @SaCheckPermission("sysUser:add")
    @Log(title = "新增用户", businessType = BusinessType.INSERT)
    public Result<String> create(@Valid @RequestBody SysUserReq req) {
        boolean flag = sysUserService.createData(req);
        return flag ? Result.ok("创建成功") : Result.error("创建失败");
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "修改", description = "权限字符串：sysUser:update")
    @SaCheckPermission("sysUser:update")
    @Log(title = "修改用户", businessType = BusinessType.UPDATE)
    public Result<String> update(@Valid @RequestBody SysUserReq req) {
        boolean flag = sysUserService.updateData(req);
        return flag ? Result.ok("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除", description = "权限字符串：sysUser:del")
    @SaCheckPermission("sysUser:del")
    @Log(title = "删除用户", businessType = BusinessType.DELETE)
    public Result<String> delete(@RequestParam("id") Long id) {
        boolean flag = sysUserService.removeData(id);
        return flag ? Result.ok("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/get")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "单条查询", description = "权限字符串：sysUser:index")
    @Log(title = "用户详情", businessType = BusinessType.QUERY)
    public Result<SysUserVO> getOne(@RequestParam("id") Long id) {
        return Result.ok(sysUserService.getData(id));
    }

    @GetMapping("/info")
    @ApiOperationSupport(order = 6)
    @Operation(summary = "用户信息")
    @Log(title = "用户信息", businessType = BusinessType.QUERY)
    public Result<SysUserVO> info() {
        return Result.ok(sysUserService.getData(StpUtil.getLoginIdAsLong()));
    }

    @GetMapping("/getRole")
    @ApiOperationSupport(order = 7)
    @Operation(summary = "角色信息")
    @Log(title = "角色信息", businessType = BusinessType.QUERY)
    public Result<List<Long>> getRoleIds(@RequestParam("userId") Long userId) {
        return Result.ok(sysUserRoleService.getRoleIdsByUserId(userId));
    }
}

