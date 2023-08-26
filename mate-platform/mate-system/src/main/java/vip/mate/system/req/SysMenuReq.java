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
 * 菜单数据Req
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(name = "SysMenuReq对象", description = "菜单数据")
public class SysMenuReq extends PageReq {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "上级ID，一级菜单为0")
    private Long pid;

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
    private Integer sort;

    @Schema(description = "是否固定")
    private Byte affix;

    @Schema(description = "标签")
    private String tag;

    @Schema(description = "隐藏菜单 0：否 1：是")
    private Byte hidden;

    @Schema(description = "隐藏面包屑 0：否 1：是")
    private Byte hiddenBreadcrumb;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "按钮列表")
    private List<ButtonMenuReq> buttonList;

}
