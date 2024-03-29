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
import vip.mate.system.req.SysClientReq;
import vip.mate.system.vo.SysClientVO;
import vip.mate.system.service.SysClientService;
import vip.mate.system.entity.SysClient;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import vip.mate.core.common.constant.MateConstant;

/**
 * <p>
 * 系统授权表 前端控制器
 * </p>
 *
 * @author matevip
 * @since 2023-08-21
 */
@RestController
@RequestMapping(MateConstant.MATE_SYSTEM_PREFIX_URL + "/client")
@AllArgsConstructor
@ApiSupport(order = 1)
@Tag(name = "客户端授权")
@Validated
public class SysClientController {

    private final SysClientService sysClientService;

    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "列表查询",description = "权限字符串：sysClient:page")
    @SaCheckPermission("sysClient:page")
    @Log(title = "客户端列表", businessType = BusinessType.QUERY)
    public Result<PageRes<SysClientVO>> queryPage(SysClientReq req) {
        return Result.ok(sysClientService.queryPage(req));
    }

    @PostMapping("/add")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "新增",description = "权限字符串：sysClient:add")
    @SaCheckPermission("sysClient:add")
    @Log(title = "新增客户端", businessType = BusinessType.INSERT)
    public Result<String> create(@Valid @RequestBody SysClient entity){
        boolean flag = sysClientService.createData(entity);
        return flag ? Result.ok("创建成功") : Result.error("创建失败");
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "修改",description = "权限字符串：sysClient:update")
    @SaCheckPermission("sysClient:update")
    @Log(title = "修改客户端", businessType = BusinessType.UPDATE)
    public Result<String> update(@Valid @RequestBody SysClient entity){
        boolean flag = sysClientService.updateData(entity);
        return flag ? Result.ok("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除",description = "权限字符串：sysClient:del")
    @SaCheckPermission("sysClient:del")
    @Log(title = "删除客户端", businessType = BusinessType.DELETE)
    public Result<String> delete(@RequestParam("id") Long id){
        boolean flag = sysClientService.removeData(id);
        return flag ? Result.ok("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/get")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "单条查询",description = "权限字符串：sysClient:index")
    @Log(title = "客户端查询", businessType = BusinessType.QUERY)
    public Result<SysClientVO> getOne(@RequestParam("id") Long id){
        return Result.ok(sysClientService.getData(id));
    }
}

