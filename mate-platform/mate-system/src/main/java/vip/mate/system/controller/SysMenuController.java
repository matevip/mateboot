package vip.mate.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import lombok.AllArgsConstructor;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.common.response.Result;
import vip.mate.core.mybatis.res.PageRes;
import vip.mate.system.req.SysMenuReq;
import vip.mate.system.vo.SysMenuVO;
import vip.mate.system.service.SysMenuService;
import vip.mate.system.entity.SysMenu;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import vip.mate.core.common.constant.MateConstant;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单数据 前端控制器
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@SaIgnore
@RestController
@RequestMapping(MateConstant.MATE_PREFIX_URL + "/sysMenu")
@AllArgsConstructor
@ApiSupport(order = 1)
@Tag(description = "sysMenu", name = "菜单数据管理")
@Validated
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "列表查询", description = "权限字符串：sysMenu:page")
    @SaCheckPermission("sysMenu:page")
    public Result<PageRes<SysMenuVO>> queryPage(SysMenuReq req) {
        return Result.ok(sysMenuService.queryPage(req));
    }

    @PostMapping("/add")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "新增", description = "权限字符串：sysMenu:add")
    @SaCheckPermission("sysMenu:add")
    public Result<String> create(@Valid @RequestBody SysMenu entity) {
        boolean flag = sysMenuService.createData(entity);
        return flag ? Result.ok("创建成功") : Result.error("创建失败");
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "修改", description = "权限字符串：sysMenu:update")
    @SaCheckPermission("sysMenu:update")
    public Result<String> update(@Valid @RequestBody SysMenu entity) {
        boolean flag = sysMenuService.updateData(entity);
        return flag ? Result.ok("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除", description = "权限字符串：sysMenu:del")
    @SaCheckPermission("sysMenu:del")
    public Result<String> delete(@RequestParam("id") Long id) {
        boolean flag = sysMenuService.removeData(id);
        return flag ? Result.ok("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/get")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "单条查询", description = "权限字符串：sysMenu:index")
    @SaCheckPermission("sysMenu:index")
    public Result<SysMenuVO> getOne(@RequestParam("id") Long id) {
        return Result.ok(sysMenuService.getData(id));
    }

    @GetMapping("/route")
    @Operation(summary = "菜单路由")
    public Result<List<SysMenuVO>> route() {
        String loginId = StpUtil.getLoginId().toString();
        System.out.println("loginId=" + loginId);
        List<SysMenuVO> routes = sysMenuService.route(loginId);
        return Result.ok(routes);
    }

    @GetMapping("/authority")
    @Operation(summary = "用户权限集合")
    public Result<Set<String>> authority() {
        String loginId = StpUtil.getLoginId().toString();
        Set<String> permissions = sysMenuService.authority(loginId);
        return Result.ok(permissions);
    }
}

