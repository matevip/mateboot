package vip.mate.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色VO
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysUserRoleVO对象", description = "用户角色")
public class SysUserRoleVO {

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "用户ID")
    private Long userId;

}
