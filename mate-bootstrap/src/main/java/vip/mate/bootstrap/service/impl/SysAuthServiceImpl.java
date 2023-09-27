package vip.mate.bootstrap.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import vip.mate.bootstrap.enums.AuthCodeEnum;
import vip.mate.bootstrap.req.SysUsernameLoginReq;
import vip.mate.bootstrap.req.SysMobileLoginReq;
import vip.mate.bootstrap.service.SysAuthService;
import vip.mate.bootstrap.service.SysCaptchaService;
import vip.mate.bootstrap.vo.SysTokenVO;
import vip.mate.core.common.exception.ServerException;
import vip.mate.core.common.utils.CryptoUtils;
import vip.mate.core.log.enums.BusinessStatus;
import vip.mate.core.log.enums.LoginType;
import vip.mate.core.satoken.entity.LoginUser;
import vip.mate.core.satoken.utils.UserInfoHelper;
import vip.mate.system.entity.SysUser;
import vip.mate.system.service.SysClientService;
import vip.mate.system.service.SysLoginLogService;
import vip.mate.system.service.SysUserService;
import vip.mate.system.vo.SysClientVO;
import vip.mate.system.vo.SysLoginLogVO;

/**
 * 权限认证服务
 *
 * @author matevip 7333791@qq.com
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysAuthServiceImpl implements SysAuthService {
    private final SysCaptchaService sysCaptchaService;
    private final SysClientService sysClientService;
    private final SysUserService sysUserService;
    private final SysLoginLogService sysLoginLogService;

    @Override
    public SysTokenVO loginByUsername(SysUsernameLoginReq login) {
        // 验证码效验
        boolean flag = sysCaptchaService.validate(login.getKey(), login.getCaptcha());
        if (!flag) {
            // 记录登录日志
            sysLoginLogService.saveLog(login.getUsername(), BusinessStatus.FAIL.ordinal(),
                    LoginType.CAPTCHA_FAIL.getValue());
            throw new ServerException(AuthCodeEnum.CAPTCHA_ERROR);
        }

        String clientId = login.getClientId();
        String grantType = login.getGrantType();
        SysClientVO sysClient = sysClientService.queryByClientId(clientId);
        // 客户端认证失败
        if (ObjectUtil.isNull(sysClient) || !StringUtils.contains(sysClient.getGrantType(), grantType)) {
            sysLoginLogService.saveLog(login.getUsername(), BusinessStatus.FAIL.ordinal(),
                    LoginType.CLIENT_FAIL.getValue());
            throw new ServerException(AuthCodeEnum.CLIENT_ERROR, clientId, grantType);
        }
        SysUser sysUser = sysUserService.queryByUsername(login.getUsername());
        // 用户不存在
        if (ObjectUtil.isNull(sysUser)) {
            sysLoginLogService.saveLog(login.getUsername(), BusinessStatus.FAIL.ordinal(),
                    LoginType.ACCOUNT_FAIL.getValue());
            throw new ServerException(AuthCodeEnum.USER_ERROR, login.getUsername(), Boolean.TRUE);
        }
        // 用户名密码不正确
        if (!CryptoUtils.doHashValue(CryptoUtils.doSm4CbcEncrypt(login.getPassword()))
                .equals(sysUser.getPassword())) {
            sysLoginLogService.saveLog(login.getUsername(), BusinessStatus.FAIL.ordinal(),
                    LoginType.ACCOUNT_FAIL.getValue());
            throw new ServerException(AuthCodeEnum.PWD_ERROR, login.getUsername(), Boolean.TRUE);
        }
        // 记录登录信息
        SaLoginModel model = new SaLoginModel();
        model.setDevice(sysClient.getDeviceType());
        model.setTimeout(sysClient.getTimeout());
        model.setActiveTimeout(sysClient.getActiveTimeout());
        StpUtil.login(sysUser.getId(), model);
        LoginUser loginUser = BeanUtil.copyProperties(sysUser, LoginUser.class);
        loginUser.setPermissionList(sysUserService.getPermissionByUserId(sysUser.getId()));
        UserInfoHelper.login(loginUser);
        sysLoginLogService.saveLog(login.getUsername(), BusinessStatus.SUCCESS.ordinal(),
                LoginType.LOGIN_SUCCESS.getValue());
        return new SysTokenVO(StpUtil.getTokenValue());
    }

    @Override
    public SysTokenVO loginByMobile(SysMobileLoginReq login) {
        String accessToken = "1";
        return new SysTokenVO(accessToken);
    }

    @Override
    public void sendCode(String mobile) {
        // 生成6位验证码
        String code = RandomUtil.randomNumbers(6);
    }

    @Override
    public void logout() {
        sysLoginLogService.saveLog(UserInfoHelper.getUsername(), BusinessStatus.SUCCESS.ordinal(),
                LoginType.LOGOUT_SUCCESS.getValue());
        StpUtil.logout();
    }
}
