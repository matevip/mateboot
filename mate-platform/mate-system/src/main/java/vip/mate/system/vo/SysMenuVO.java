package vip.mate.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import vip.mate.core.common.entity.TreeNode;

/**
 * <p>
 * 菜单数据VO
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysMenuVO对象", description = "菜单数据")
public class SysMenuVO extends TreeNode<SysMenuVO> {

    @Schema(description = "别名")
    private String name;

    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "菜单URL")
    private String path;

    @Schema(description = "组件")
    private String component;

    @Schema(description = "授权标识")
    private String authority;

    @Schema(description = "类型   0：菜单   1：按钮   2：接口")
    private Byte type;

    @Schema(description = "打开方式   0：内部   1：外部")
    private Byte openStyle;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "排序")
    private String sort;

    @Schema(description = "是否固定")
    private int affix;

    @Schema(description = "标签")
    private String tag;

    @Schema(description = "隐藏菜单 0：否 1：是")
    private byte hidden;

    @Schema(description = "隐藏面包屑 0：否 1：是")
    private byte hiddenBreadcrumb;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "菜单元数据")
    private Meta meta;


}
