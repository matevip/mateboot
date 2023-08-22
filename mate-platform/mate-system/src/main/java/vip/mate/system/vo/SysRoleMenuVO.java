package vip.mate.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单VO
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysRoleMenuVO对象", description = "角色菜单")
public class SysRoleMenuVO {

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "菜单ID")
    private Long menuId;

}
