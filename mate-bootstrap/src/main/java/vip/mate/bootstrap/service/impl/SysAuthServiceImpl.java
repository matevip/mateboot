package vip.mate.bootstrap.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
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
import vip.mate.bootstrap.utils.CryptoUtil;
import vip.mate.bootstrap.vo.SysTokenVO;
import vip.mate.core.common.enums.ErrorCode;
import vip.mate.core.common.exception.ServerException;
import vip.mate.system.entity.SysUser;
import vip.mate.system.service.SysClientService;
import vip.mate.system.service.SysUserService;
import vip.mate.system.vo.SysClientVO;

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

    @Override
    public SysTokenVO loginByUsername(SysUsernameLoginReq login) {
        // 验证码效验
        boolean flag = sysCaptchaService.validate(login.getKey(), login.getCaptcha());
        if (!flag) {
            // 保存登录日志
            throw new ServerException(AuthCodeEnum.CAPTCHA_ERROR);
        }

        String clientId = login.getClientId();
        String grantType = login.getGrantType();
        SysClientVO sysClient = sysClientService.queryByClientId(clientId);
        // 客户端认证失败
        if (ObjectUtil.isNull(sysClient) || !StringUtils.contains(sysClient.getGrantType(), grantType)) {
            throw new ServerException(AuthCodeEnum.CLIENT_ERROR, clientId, grantType);
        }
        SysUser sysUser = sysUserService.queryByUsername(login.getUsername());
        // 用户不存在
        if (ObjectUtil.isNull(sysUser)) {
            throw new ServerException(AuthCodeEnum.USER_ERROR, login.getUsername(), Boolean.TRUE);
        }
        // 用户名密码不正确
        if (!CryptoUtil.doHashValue(CryptoUtil.doSm4CbcEncrypt(login.getPassword()))
                .equals(sysUser.getPassword())) {
            throw new ServerException(AuthCodeEnum.PWD_ERROR, login.getUsername(), Boolean.TRUE);
        }
        // Sa-Token 认证
        SaLoginModel model = new SaLoginModel();
        model.setDevice(sysClient.getDeviceType());
        model.setTimeout(sysClient.getTimeout());
        model.setActiveTimeout(sysClient.getActiveTimeout());
        StpUtil.login(sysUser.getId(), model);
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
        StpUtil.logout();
    }
}
