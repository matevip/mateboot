package vip.mate.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import vip.mate.core.common.entity.TreeNode;

/**
 * <p>
 * 部门VO
 * </p>
 *
 * @author matevip
 * @since 2023-08-24
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysDeptVO对象", description = "部门")
public class SysDeptVO extends TreeNode<SysDeptVO> {

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "部门备注")
    private String remark;

    @Schema(description = "部门负责人")
    private Long ownerUserId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "版本号")
    private Integer version;

}
