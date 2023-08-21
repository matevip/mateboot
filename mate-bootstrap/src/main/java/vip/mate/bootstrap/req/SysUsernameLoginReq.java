package vip.mate.bootstrap.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 账号登录
 *
 * @author matevip
 * @date 2023/8/21
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Data
@Schema(description = "账号登录")
public class SysUsernameLoginReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "唯一key")
    private String key;

    @Schema(description = "验证码")
    private String captcha;

    @Schema(description = "客户端ID")
    private String clientId;

    @Schema(description = "授权类型")
    private String grantType;
}
