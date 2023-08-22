package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import vip.mate.core.mybatis.entity.MateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 用户角色
* </p>
*
* @author matevip
* @since 2023-08-22
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_user_role")
@Schema(name = "SysUserRole对象", description = "用户角色")
public class SysUserRole extends MateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "角色ID")
    @TableField("role_id")
    private Long roleId;

    @Schema(description = "用户ID")
    @TableField("user_id")
    private Long userId;



}
