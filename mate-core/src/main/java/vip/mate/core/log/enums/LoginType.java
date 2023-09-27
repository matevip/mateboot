package vip.mate.core.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录枚举类
 *
 * @author matevip
 * @since 2023/9/27
 */
@Getter
@AllArgsConstructor
public enum LoginType {

    /**
     * 登录成功
     */
    LOGIN_SUCCESS(0),

    /**
     * 退出成功
     */
    LOGOUT_SUCCESS(1),

    /**
     * 验证码错误
     */
    CAPTCHA_FAIL(2),

    /**
     * 客户端错误
     */
    CLIENT_FAIL(3),

    /**
     * 账号密码错误
     */
    ACCOUNT_FAIL(4);

    private final int value;
}
