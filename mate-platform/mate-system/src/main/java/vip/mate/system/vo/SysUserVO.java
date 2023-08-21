package vip.mate.system.vo;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户管理VO
 * </p>
 *
 * @author matevip
 * @since 2023-08-21
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysUserVO对象", description = "用户管理")
public class SysUserVO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别   0：男   1：女   2：未知")
    private Byte gender;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "机构ID")
    private Long orgId;

    @Schema(description = "超级管理员   0：否   1：是")
    private Byte superAdmin;

    @Schema(description = "状态  0：停用   1：正常")
    private Byte status;

    @Schema(description = "版本号")
    private Integer version;

}
