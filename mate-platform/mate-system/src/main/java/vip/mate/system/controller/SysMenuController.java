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
import vip.mate.core.common.response.R;
import vip.mate.core.common.response.Result;
import vip.mate.core.mybatis.res.PageRes;
import vip.mate.system.enums.MenuTypeEnum;
import vip.mate.system.req.SysMenuReq;
import vip.mate.system.service.SysUserService;
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
@RestController
@RequestMapping(MateConstant.MATE_SYSTEM_PREFIX_URL + "/menu")
@AllArgsConstructor
@ApiSupport(order = 1)
@Tag(name = "菜单数据")
@Validated
public class SysMenuController {

    private final SysMenuService sysMenuService;
    private final SysUserService sysUserService;

    @GetMapping("/route")
    @Operation(summary = "菜单路由")
    @ApiOperationSupport(order = 1)
    public Result<List<SysMenuVO>> route() {
        String loginId = StpUtil.getLoginId().toString();
        List<SysMenuVO> routes = sysUserService.route(loginId);
        return Result.ok(routes);
    }

    @GetMapping("/authority")
    @Operation(summary = "用户权限集合")
    @ApiOperationSupport(order = 2)
    public Result<Set<String>> authority() {
        String loginId = StpUtil.getLoginId().toString();
        Set<String> permissions = sysMenuService.authority(loginId);
        return Result.ok(permissions);
    }

    @GetMapping("/list")
    @Operation(summary = "菜单列表")
    @ApiOperationSupport(order = 3)
    @SaCheckPermission("sysMenu:list")
    public Result<List<SysMenuVO>> list(Integer type){
        List<SysMenuVO> list = sysMenuService.getMenuList(type);
        return R.ok(list);
    }

    @GetMapping("/buttons")
    @Operation(summary = "按钮列表")
    @ApiOperationSupport(order = 4)
    public Result<List<SysMenuVO>> buttons(Long id){
        List<SysMenuVO> list = sysMenuService.getButtonList(id, MenuTypeEnum.BUTTON.getValue());
        return R.ok(list);
    }

    @PostMapping("/saveAll")
    @Operation(summary = "保存菜单")
    @ApiOperationSupport(order = 5)
    @SaCheckPermission("sysMenu:update")
    public Result<String> saveAll(@Valid @RequestBody SysMenuReq req) {
        return R.ok(sysMenuService.saveAll(req));
    }

    @PostMapping("/deleteAll")
    @Operation(summary = "删除菜单")
    @ApiOperationSupport(order = 6)
    @SaCheckPermission("sysMenu:del")
    public Result<String> deleteAll(@RequestBody Long[] ids) {
        sysMenuService.deleteAll(ids);
        return R.ok();
    }
}

