package vip.mate.bootstrap.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 手机号登录
 *
 * @author matevip
 * @date 2023/8/21
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Data
@Schema(description = "手机号登录")
public class SysMobileLoginReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "验证码")
    private String code;
}
