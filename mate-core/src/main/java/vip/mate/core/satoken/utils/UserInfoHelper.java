package vip.mate.core.satoken.utils;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vip.mate.core.common.utils.IpUtils;
import vip.mate.core.common.utils.ServletUtils;
import vip.mate.core.satoken.constant.LoginConstant;
import vip.mate.core.satoken.entity.LoginUser;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户信息助手
 *
 * @author matevip
 * @date 2023/9/26
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoHelper {

    /**
     * 用户登录并缓存用户信息
     *
     * @return LoginUser 登录用户信息
     */
    public static void login(LoginUser loginUser) {
        // 记录登录信息
        loginUser.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        loginUser.setAddress(IpUtils.getCityInfo(loginUser.getIp()));
        loginUser.setBrowser(ServletUtils.getBrowser(ServletUtils.getRequest()));
        loginUser.setLoginTime(new Date());
        // 登录并缓存用户信息
        SaHolder.getStorage().set(LoginConstant.LOGIN_USER_KEY, loginUser);
        loginUser.setToken(StpUtil.getTokenValue());
        StpUtil.getTokenSession().set(LoginConstant.LOGIN_USER_KEY, loginUser);
    }

    /**
     * 获取登录用户信息
     *
     * @return LoginUser 登录用户信息（获取 TokenSession 时如未登录，会抛出异常）
     */
    public static LoginUser getLoginUser() {
        LoginUser loginUser = (LoginUser) SaHolder.getStorage().get(LoginConstant.LOGIN_USER_KEY);
        if (loginUser == null) {
            loginUser = (LoginUser) StpUtil.getTokenSession().get(LoginConstant.LOGIN_USER_KEY);
        }
        SaHolder.getStorage().set(LoginConstant.LOGIN_USER_KEY, loginUser);
        return loginUser;
    }

    /**
     * 根据token获取登录用户信息
     *
     * @param token token
     * @return LoginUser 登录用户信息（获取 TokenSession 时如未登录，会抛出异常）
     */
    public static LoginUser getLoginUser(String token) {
        SaSession tokenSession = StpUtil.getTokenSessionByToken(token);
        if(tokenSession == null){
            return null;
        }
        return (LoginUser) tokenSession.get(LoginConstant.LOGIN_USER_KEY);
    }

    /**
     * 获取登录用户ID
     *
     * @return Long 登录用户ID
     */
    public static Long getUserId() {
        return getLoginUser().getId();
    }

    /**
     * 获取登录用户名称
     *
     * @return String 登录用户名称
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 获取登录用户真实姓名
     *
     * @return String 登录用户真实姓名
     */
    public static String getRealName() {
        return getLoginUser().getRealName();
    }
}
