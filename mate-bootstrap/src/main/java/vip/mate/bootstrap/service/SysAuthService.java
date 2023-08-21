package vip.mate.bootstrap.service;

import vip.mate.bootstrap.req.SysUsernameLoginReq;
import vip.mate.bootstrap.req.SysMobileLoginReq;
import vip.mate.bootstrap.vo.SysTokenVO;

/**
 * 权限认证服务
 *
 * @author matevip 7333791@qq.com
 */
public interface SysAuthService {

    /**
     * 账号密码登录
     *
     * @param login 登录信息
     */
    SysTokenVO loginByUsername(SysUsernameLoginReq login);

    /**
     * 手机短信登录
     *
     * @param login 登录信息
     */
    SysTokenVO loginByMobile(SysMobileLoginReq login);

    /**
     * 发送手机验证码
     *
     * @param mobile 手机号
     */
    void sendCode(String mobile);

    /**
     * 退出登录
     *
     * @param accessToken accessToken
     */
    void logout(String accessToken);
}
