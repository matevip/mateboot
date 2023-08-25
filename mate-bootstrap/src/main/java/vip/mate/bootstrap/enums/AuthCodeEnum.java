package vip.mate.bootstrap.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vip.mate.core.common.enums.ResultCode;

/**
 * todo
 *
 * @author matevip
 * @since 2023/8/25
 */
@Getter
@AllArgsConstructor
public enum AuthCodeEnum implements ResultCode {

    CLIENT_ERROR(500, "客户端认证失败"),
    USER_ERROR(500, "用户不存在"),
    PWD_ERROR(500, "密码不正确"),
    CAPTCHA_ERROR(500, "验证码错误");

    private final int code;
    private final String msg;
}
