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
import vip.mate.core.mybatis.res.PageRes;
import vip.mate.system.req.SysRoleReq;
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
@Tag(description = "sysRole", name = "角色管理管理")
@Validated
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "列表查询",description = "权限字符串：sysRole:page")
//    @SaCheckPermission("sysRole:page")
    public Result<PageRes<SysRoleVO>> queryPage(SysRoleReq req) {
        return Result.ok(sysRoleService.queryPage(req));
    }

    @GetMapping("/list")
    @ApiOperationSupport(order = 7)
    @Operation(summary = "列表查询",description = "权限字符串：sysRole:list")
    public Result<List<SysRoleVO>> list() {
        List<SysRoleVO> list = sysRoleService.getList(new SysRoleReq());
        return Result.ok(list);
    }

    @PostMapping("/add")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "新增",description = "权限字符串：sysRole:add")
//    @SaCheckPermission("sysRole:add")
    public Result<String> create(@Valid @RequestBody SysRole entity){
        boolean flag = sysRoleService.createData(entity);
        return flag ? Result.ok("创建成功") : Result.error("创建失败");
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "修改",description = "权限字符串：sysRole:update")
//    @SaCheckPermission("sysRole:update")
    public Result<String> update(@Valid @RequestBody SysRole entity){
        boolean flag = sysRoleService.updateData(entity);
        return flag ? Result.ok("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除",description = "权限字符串：sysRole:del")
//    @SaCheckPermission("sysRole:del")
    public Result<String> delete(@RequestParam("id") Long id){
        boolean flag = sysRoleService.removeData(id);
        return flag ? Result.ok("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/get")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "单条查询",description = "权限字符串：sysRole:index")
//    @SaCheckPermission("sysRole:index")
    public Result<SysRoleVO> getOne(@RequestParam("id") Long id){
        return Result.ok(sysRoleService.getData(id));
    }


    @PutMapping("/updateStatus")
    @ApiOperationSupport(order = 6)
    @Operation(summary = "状态更新")
//    @SaCheckPermission("sysRole:status")
    public Result<String> updateStatus(@Valid @RequestBody SysRole entity) {
        sysRoleService.update(Wrappers.<SysRole>update().lambda().eq(SysRole::getId, entity.getId()).set(SysRole::getStatus, entity.getStatus()));
        return R.ok();
    }
}

