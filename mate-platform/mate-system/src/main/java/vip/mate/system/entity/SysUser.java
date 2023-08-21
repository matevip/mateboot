package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import vip.mate.core.mybatis.entity.MateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 用户管理
* </p>
*
* @author matevip
* @since 2023-08-21
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_user")
@Schema(name = "SysUser对象", description = "用户管理")
public class SysUser extends MateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "姓名")
    @TableField("real_name")
    private String realName;

    @Schema(description = "头像")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "性别   0：男   1：女   2：未知")
    @TableField("gender")
    private Byte gender;

    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "手机号")
    @TableField("mobile")
    private String mobile;

    @Schema(description = "机构ID")
    @TableField("org_id")
    private Long orgId;

    @Schema(description = "超级管理员   0：否   1：是")
    @TableField("super_admin")
    private Byte superAdmin;

    @Schema(description = "状态  0：停用   1：正常")
    @TableField("status")
    private Byte status;

    @Schema(description = "版本号")
    @TableField("version")
    private Integer version;



}
