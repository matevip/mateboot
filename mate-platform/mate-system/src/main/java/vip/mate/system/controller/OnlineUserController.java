package vip.mate.system.controller;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.response.Result;
import vip.mate.core.satoken.entity.LoginUser;
import vip.mate.core.satoken.utils.UserInfoHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * todo
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

    @RequestMapping("/test")
    public Result<List<LoginUser>> test() {
        List<LoginUser> loginUserList = new ArrayList<>();
        List<String> tokenKeyList = StpUtil.searchTokenValue("", 0, -1, false);
        for (String tokenKey : tokenKeyList) {
            String token = StrUtil.subAfter(tokenKey, ":", true);
            // 忽略已过期或失效 Token
            if (StpUtil.stpLogic.getTokenActiveTimeoutByToken(token) < SaTokenDao.NEVER_EXPIRE) {
                continue;
            }
           LoginUser loginUser = UserInfoHelper.getLoginUser(token);
            loginUserList.add(loginUser);
        }
        // 设置排序
        CollUtil.sort(loginUserList, Comparator.comparing(LoginUser::getLoginTime).reversed());
        return Result.ok(loginUserList);
    }


}
