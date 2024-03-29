package vip.mate.bootstrap.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.mate.bootstrap.req.SysUsernameLoginReq;
import vip.mate.bootstrap.service.SysAuthService;
import vip.mate.bootstrap.service.SysCaptchaService;
import vip.mate.bootstrap.vo.SysCaptchaVO;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.response.Result;

/**
 * 认证控制器
 *
 * @author matevip
 * @date 2023/8/20
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 **/
@SaIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping(MateConstant.MATE_PREFIX_URL + "/auth")
@ApiSupport(author = "MateVip", order = 2)
@Tag(name = "认证管理", description = "采用sa-token实现的多认证登录")
public class AuthController {

    private final SysCaptchaService sysCaptchaService;
    private final SysAuthService sysAuthService;

    @PostMapping("/login")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "登录")
    public Result login(@Validated @RequestBody SysUsernameLoginReq login){
        return Result.ok(sysAuthService.loginByUsername(login));
    }

    @GetMapping("/captcha")
    @Operation(summary = "获取验证码")
    public Result<SysCaptchaVO> captcha() {
        SysCaptchaVO captchaVO = sysCaptchaService.generate();
        return Result.ok(captchaVO);
    }

    @PostMapping("/logout")
    public Result<String> logout() {
        sysAuthService.logout();
        return Result.ok("退出成功");
    }

}
