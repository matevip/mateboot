package vip.mate.system.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.TransPojo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录日志VO
 * </p>
 *
 * @author matevip
 * @since 2023-09-27
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysLoginLogVO对象", description = "登录日志")
public class SysLoginLogVO implements Serializable, TransPojo {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "登录IP")
    private String ip;

    @Schema(description = "登录地点")
    private String address;

    @Schema(description = "浏览器")
    private String browser;

    @Schema(description = "操作系统")
    private String os;

    @Schema(description = "登录状态  0：失败   1：成功")
    private Integer status;

    @Trans(type = TransType.DICTIONARY, key = "login_type", ref = "businessTypeLabel")
    @Schema(description =  "操作信息   0：登录成功   1：退出成功  2：验证码错误 3: 客户端错误   4：账号密码错误")
    private Integer businessType;

    @Schema(description = "操作信息标签")
    private String businessTypeLabel;

    @Schema(description = "创建时间")
    private Date createTime;

}
