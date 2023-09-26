package vip.mate.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.response.Result;
import vip.mate.core.mybatis.res.PageRes;
import vip.mate.system.req.OnlineUserReq;
import vip.mate.system.service.OnlineUserService;
import vip.mate.system.vo.OnlineUserVO;


/**
 * 在线用户管理
 *
 * @author matevip
 * @since 2023/9/26
 */
@RestController
@RequestMapping(MateConstant.MATE_SYSTEM_PREFIX_URL + "/online/user")
@AllArgsConstructor
@ApiSupport(order = 1)
@Tag(name = "在线用户管理")
@Validated
public class OnlineUserController {

    private final OnlineUserService onlineUserService;

    @RequestMapping("/page")
    @Operation(summary = "在线用户分页查询")
    public Result<PageRes<OnlineUserVO>> page(OnlineUserReq req) {
        return Result.ok(onlineUserService.page(req));
    }

    @DeleteMapping("/{token}")
    @Operation(summary = "强退在线用户")
    public Result<String> kickOut(@PathVariable String token) {
        String currentToken = StpUtil.getTokenValue();
        if (token.equals(currentToken)) {
            return Result.error("不能强退自己");
        }
        StpUtil.kickoutByTokenValue(token);
        return Result.ok("强退成功");
    }
}
