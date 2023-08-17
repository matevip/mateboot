package vip.mate.bootstrap.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.config.MateConfig;
import vip.mate.core.common.response.Result;

/**
 * 默认首页
 *
 * @author matevip
 * @since 2023/8/16
 */
@RestController
@RequiredArgsConstructor
public class IndexController {

    /**
     * 系统基础配置
     */
    private final MateConfig mateConfig;

    @GetMapping("/")
    public String index() {
        return StrUtil.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", mateConfig.getName(), mateConfig.getVersion());
    }

    @SaCheckLogin
    @GetMapping("/admin/get-version")
    public Result getVersion() {
        return Result.ok(mateConfig.getVersion());
    }
}
