package vip.mate.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.common.response.R;
import vip.mate.core.common.response.Result;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.log.enums.BusinessType;
import vip.mate.core.mybatis.res.PageRes;
import vip.mate.system.req.SysRoleReq;
import vip.mate.system.service.SysRoleMenuService;
import vip.mate.system.vo.SysRoleVO;
import vip.mate.system.service.SysRoleService;
import vip.mate.system.entity.SysRole;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import vip.mate.core.common.constant.MateConstant;

import java.util.List;

/**
 * <p>
 * 角色管理 前端控制器
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@SaIgnore
@RestController
@RequestMapping(MateConstant.MATE_SYSTEM_PREFIX_URL + "/role")
@AllArgsConstructor
@ApiSupport(order = 1)
@Tag(name = "角色管理管理")
@Validated
public class SysRoleController {

    private final SysRoleService sysRoleService;
    private final SysRoleMenuService sysRoleMenuService;

    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "列表查询",description = "权限字符串：sysRole:page")
    @SaCheckPermission("sysRole:list")
    @Log(title = "角色分页", businessType = BusinessType.QUERY)
    public Result<PageRes<SysRoleVO>> queryPage(SysRoleReq req) {
        return Result.ok(sysRoleService.queryPage(req));
    }

    @GetMapping("/list")
    @ApiOperationSupport(order = 7)
    @Operation(summary = "列表查询",description = "权限字符串：sysRole:list")
    @SaCheckPermission("sysRole:list")
    @Log(title = "角色列表", businessType = BusinessType.QUERY)
    public Result<List<SysRoleVO>> list() {
        List<SysRoleVO> list = sysRoleService.getList(new SysRoleReq());
        return Result.ok(list);
    }

    @PostMapping("/add")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "新增",description = "权限字符串：sysRole:add")
    @SaCheckPermission("sysRole:add")
    @Log(title = "新增角色", businessType = BusinessType.INSERT)
    public Result<String> create(@Valid @RequestBody SysRole entity){
        boolean flag = sysRoleService.createData(entity);
        return flag ? Result.ok("创建成功") : Result.error("创建失败");
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "修改",description = "权限字符串：sysRole:update")
    @SaCheckPermission("sysRole:update")
    @Log(title = "修改角色", businessType = BusinessType.UPDATE)
    public Result<String> update(@Valid @RequestBody SysRoleReq req){
        boolean flag = sysRoleService.updateData(req);
        return flag ? Result.ok("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除",description = "权限字符串：sysRole:del")
    @SaCheckPermission("sysRole:del")
    @Log(title = "删除角色", businessType = BusinessType.DELETE)
    public Result<String> delete(@RequestParam("id") Long id){
        boolean flag = sysRoleService.removeData(id);
        return flag ? Result.ok("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/get")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "单条查询",description = "权限字符串：sysRole:index")
    @Log(title = "角色查询", businessType = BusinessType.QUERY)
    public Result<SysRoleVO> getOne(@RequestParam("id") Long id){
        return Result.ok(sysRoleService.getData(id));
    }


    @PutMapping("/updateStatus")
    @ApiOperationSupport(order = 6)
    @Operation(summary = "状态更新")
    @SaCheckPermission("sysRole:update")
    @Log(title = "状态更新", businessType = BusinessType.UPDATE)
    public Result<String> updateStatus(@Valid @RequestBody SysRole entity) {
        sysRoleService.update(Wrappers.<SysRole>update().lambda().eq(SysRole::getId, entity.getId()).set(SysRole::getStatus, entity.getStatus()));
        return R.ok();
    }

    @GetMapping("/getMenuIds")
    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取角色菜单")
    @Log(title = "获取角色菜单", businessType = BusinessType.QUERY)
    public Result<List<Long>> getMenuIds(@RequestParam("roleId") Long roleId) {
        return Result.ok(sysRoleMenuService.getMenuIdsByRoleId(roleId));
    }

    @PostMapping("/updateRoleMenu")
    @ApiOperationSupport(order = 8)
    @Operation(summary = "更新角色菜单")
    @SaCheckPermission("sysRole:update")
    @Log(title = "更新角色菜单", businessType = BusinessType.UPDATE)
    public Result<String> updateRoleMenu(@Valid @RequestBody SysRoleReq req) {
        sysRoleMenuService.saveRoleMenu(req.getId(), req.getMenuIds());
        return R.ok();
    }
}

