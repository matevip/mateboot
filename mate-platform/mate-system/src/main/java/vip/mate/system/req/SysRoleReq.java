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
 * 角色管理Req
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(name = "SysRoleReq对象", description = "角色管理")
public class SysRoleReq extends PageReq {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "编码")
    private String code;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "数据范围  0：全部数据  1：本部门及子部门数据  2：本部门数据  3：本人数据  4：自定义数据")
    private Byte dataScope;

    @Schema(description = "机构ID")
    private Long orgId;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "状态")
    private Byte status;

    @Schema(description = "菜单ID集合")
    private List<Long> menuIds;

    @Schema(description = "部门ID集合")
    private List<Long> deptIds;

}
