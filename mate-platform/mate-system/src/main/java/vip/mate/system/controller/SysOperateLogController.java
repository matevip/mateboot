package vip.mate.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.AllArgsConstructor;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.common.response.Result;
import vip.mate.core.log.req.SysOperateLogReq;
import vip.mate.core.mybatis.res.PageRes;
import vip.mate.system.entity.SysOperateLog;
import vip.mate.system.vo.SysOperateLogVO;
import vip.mate.system.service.SysOperateLogService;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import vip.mate.core.common.constant.MateConstant;

/**
 * <p>
 * 操作日志 前端控制器
 * </p>
 *
 * @author matevip
 * @since 2023-09-25
 */
@RestController
@RequestMapping(MateConstant.MATE_SYSTEM_PREFIX_URL + "/sysOperateLog")
@AllArgsConstructor
@ApiSupport(author = "matevip", order = 1)
@Tag(description = "sysOperateLog", name = "操作日志管理")
@Validated
public class SysOperateLogController {

    private final SysOperateLogService sysOperateLogService;

    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "列表查询",description = "权限字符串：sysOperateLog:page")
    @SaCheckPermission("sysOperateLog:page")
    public Result<PageRes<SysOperateLogVO>> queryPage(SysOperateLogReq req) {
        return Result.ok(sysOperateLogService.queryPage(req));
    }

    @PostMapping("/add")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "新增",description = "权限字符串：sysOperateLog:add")
    @SaCheckPermission("sysOperateLog:add")
    public Result<String> create(@Valid @RequestBody SysOperateLog entity){
        boolean flag = sysOperateLogService.createData(entity);
        return flag ? Result.ok("创建成功") : Result.error("创建失败");
    }

    @PutMapping("/update")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "修改",description = "权限字符串：sysOperateLog:update")
    @SaCheckPermission("sysOperateLog:update")
    public Result<String> update(@Valid @RequestBody SysOperateLog entity){
        boolean flag = sysOperateLogService.updateData(entity);
        return flag ? Result.ok("修改成功") : Result.error("修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除",description = "权限字符串：sysOperateLog:del")
    @SaCheckPermission("sysOperateLog:del")
    public Result<String> delete(@RequestParam("id") Long id){
        boolean flag = sysOperateLogService.removeData(id);
        return flag ? Result.ok("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/get")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "单条查询",description = "权限字符串：sysOperateLog:index")
    @SaCheckPermission("sysOperateLog:index")
    public Result<SysOperateLogVO> getOne(@RequestParam("id") Long id){
        return Result.ok(sysOperateLogService.getData(id));
    }
}

