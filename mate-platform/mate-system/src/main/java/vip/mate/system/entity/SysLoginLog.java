package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import vip.mate.core.mybatis.entity.MateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 登录日志
* </p>
*
* @author matevip
* @since 2023-09-27
*/
@Data
@Accessors(chain = true)
@TableName("mate_sys_login_log")
@Schema(name = "SysLoginLog对象", description = "登录日志")
public class SysLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "姓名")
    @TableField("real_name")
    private String realName;

    @Schema(description = "登录IP")
    @TableField("ip")
    private String ip;

    @Schema(description = "登录地点")
    @TableField("address")
    private String address;

    @Schema(description = "浏览器")
    @TableField("browser")
    private String browser;

    @Schema(description = "操作系统")
    @TableField("os")
    private String os;

    @Schema(description = "登录状态  0：功能   1：失败")
    @TableField("status")
    private int status;

    @Schema(description =  "操作信息   0：登录成功   1：退出成功  2：验证码错误 3: 客户端错误   4：账号密码错误")
    @TableField("business_type")
    private int businessType;

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private Date createTime;

}
