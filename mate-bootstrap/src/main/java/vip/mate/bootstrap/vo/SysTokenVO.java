package vip.mate.bootstrap.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户Token
 *
 * @author matevip
 * @date 2023/8/21
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Data
@AllArgsConstructor
@Schema(description = "用户登录")
public class SysTokenVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "access_token")
    private String access_token;
}
