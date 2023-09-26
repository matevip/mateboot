package vip.mate.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 在线用户信息
 *
 * @author matevip
 * @since 2023/9/26
 */
@Data
@Accessors(chain = true)
@Schema(description = "在线用户信息")
public class OnlineUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID")
    private Long id;

    /**
     * 令牌
     */
    @Schema(description = "令牌")
    private String token;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String realName;

    /**
     * 登录 IP
     */
    @Schema(description = "登录 IP")
    private String ip;

    /**
     * 登录地点
     */
    @Schema(description = "登录地点")
    private String address;

    /**
     * 浏览器
     */
    @Schema(description = "浏览器")
    private String browser;

    /**
     * 登录时间
     */
    @Schema(description = "登录时间")
    private String loginTime;
}
