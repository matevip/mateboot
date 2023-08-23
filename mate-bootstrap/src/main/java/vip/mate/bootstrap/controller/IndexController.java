package vip.mate.bootstrap.controller;

import cn.hutool.core.util.StrUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.config.MateConfig;
import vip.mate.core.common.response.Result;

/**
 * 默认首页
 *
 * @author matevip
 * @date 2023/8/21
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@RestController
@RequiredArgsConstructor
@ApiSupport(order = 1)
@Tag(name = "首页", description = "系统首页")
public class IndexController {

    /**
     * 系统基础配置
     */
    private final MateConfig mateConfig;

    @GetMapping("/")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "首页")
    public String index() {
        return StrUtil.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", mateConfig.getName(), mateConfig.getVersion());
    }

    @GetMapping("/version")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "系统版本")
    public Result<String> getVersion() {
        return Result.ok(mateConfig.getVersion());
    }
}
