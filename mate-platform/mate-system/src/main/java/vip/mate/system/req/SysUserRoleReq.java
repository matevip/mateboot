package vip.mate.system.req;

import vip.mate.core.mybatis.req.PageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 用户角色Req
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(name = "SysUserRoleReq对象", description = "用户角色")
public class SysUserRoleReq extends PageReq {

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "用户ID")
    private Long userId;

}
