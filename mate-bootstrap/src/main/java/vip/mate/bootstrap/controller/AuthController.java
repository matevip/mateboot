package vip.mate.bootstrap.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/auth")
@ApiSupport(author = "matevip", order = 1)
@Tag(name = "认证登录", description = "采用sa-token实现的多认证登录")
public class AuthController {

    @GetMapping("/login")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "登录")
    public Result login(){
        return Result.ok();
    }

}
