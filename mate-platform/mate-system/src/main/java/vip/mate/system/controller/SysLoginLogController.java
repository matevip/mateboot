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
import vip.mate.system.req.SysLoginLogReq;
import vip.mate.system.vo.SysLoginLogVO;
import vip.mate.system.service.SysLoginLogService;
import vip.mate.system.entity.SysLoginLog;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import vip.mate.core.common.constant.MateConstant;

/**
 * <p>
 * 登录日志 前端控制器
 * </p>
 *
 * @author matevip
 * @since 2023-09-27
 */
@RestController
@RequestMapping(MateConstant.MATE_SYSTEM_PREFIX_URL + "/login/log")
@AllArgsConstructor
@ApiSupport(order = 1)
@Tag(name = "登录日志管理")
@Validated
public class SysLoginLogController {

    private final SysLoginLogService sysLoginLogService;

    @GetMapping("/page")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "列表查询",description = "权限字符串：sysLoginLog:page")
    @SaCheckPermission("sysLoginLog:page")
    public Result<PageRes<SysLoginLogVO>> queryPage(SysLoginLogReq req) {
        return Result.ok(sysLoginLogService.queryPage(req));
    }
}

