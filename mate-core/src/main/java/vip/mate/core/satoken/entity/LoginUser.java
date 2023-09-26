package vip.mate.core.satoken.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 登录用户信息
 *
 * @author matevip
 * @date 2023/9/26
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID")
    private Long id;

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
     * 权限码集合
     */
    @Schema(description = "权限码集合")
    private List<String> permissionList;

    /**
     * 角色编码集合
     */
    @Schema(description = "角色编码集合")
    private List<String> roleList;

    /**
     * 令牌
     */
    @Schema(description = "令牌")
    private String token;


    /**
     * 登录IP
     */
    @Schema(description = "登录IP")
    private String ip;

    /**
     * 登录地址
     */
    @Schema(description = "登录地址")
    private String address;

    /**
     * 浏览器类型
     */
    @Schema(description = "浏览器类型")
    private String browser;

    /**
     * 登录时间
     */
    @Schema(description = "登录时间")
    private Date loginTime;

}
