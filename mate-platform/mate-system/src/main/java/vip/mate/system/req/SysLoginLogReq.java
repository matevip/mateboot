package vip.mate.system.req;

import vip.mate.core.mybatis.req.PageReq;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 登录日志Req
 * </p>
 *
 * @author matevip
 * @since 2023-09-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(name = "SysLoginLogReq对象", description = "登录日志")
public class SysLoginLogReq extends PageReq {

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

    @Schema(description = "登录状态  0：成功   1：失败")
    private int status;

    @Schema(description = "操作信息   0：登录成功   1：退出成功  2：验证码错误 3: 客户端错误   4：账号密码错误")
    private int businessType;

    @Schema(description = "创建时间")
    private List<String> createTime;

}
