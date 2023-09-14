package vip.mate.demo.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.response.R;
import vip.mate.core.common.response.Result;

/**
 * 演示控制器
 *
 * @author matevip
 * @date 2023/9/14
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 **/
@RestController
@RequestMapping(MateConstant.MATE_DEMO_PREFIX_URL)
@AllArgsConstructor
@ApiSupport(order = 1)
@Tag(name = "演示模块")
@Validated
public class DemoController {

    @GetMapping("/test")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "演示测试")
    public Result<String> test() {
        return R.ok("演示测试成功");
    }
}
