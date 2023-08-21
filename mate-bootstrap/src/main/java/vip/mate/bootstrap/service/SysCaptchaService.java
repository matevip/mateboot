package vip.mate.bootstrap.service;

import vip.mate.bootstrap.vo.SysCaptchaVO;

/**
 * 验证码
 *
 * @author matevip
 * @date 2023/8/20
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
public interface SysCaptchaService {
    /**
     * 生成验证码
     */
    SysCaptchaVO generate();

    /**
     * 验证码效验
     *
     * @param key  key
     * @param code 验证码
     * @return true：成功  false：失败
     */
    boolean validate(String key, String code);
}
