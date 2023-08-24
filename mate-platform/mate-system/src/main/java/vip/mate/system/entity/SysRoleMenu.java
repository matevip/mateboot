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
* 角色菜单
* </p>
*
* @author matevip
* @since 2023-08-22
*/
@Data
@Accessors(chain = true)
@TableName("mate_sys_role_menu")
@Schema(name = "SysRoleMenu对象", description = "角色菜单")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "角色ID")
    @TableField("role_id")
    private Long roleId;

    @Schema(description = "菜单ID")
    @TableField("menu_id")
    private Long menuId;

}
