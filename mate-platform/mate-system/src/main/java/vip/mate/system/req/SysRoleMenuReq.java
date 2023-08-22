package vip.mate.system.req;

import vip.mate.core.mybatis.req.PageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 角色菜单Req
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(name = "SysRoleMenuReq对象", description = "角色菜单")
public class SysRoleMenuReq extends PageReq {

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "菜单ID")
    private Long menuId;

}
