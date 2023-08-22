package vip.mate.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 标签元对象
 *
 * @author matevip
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meta {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "是否隐藏菜单")
    private byte hidden;

    @Schema(description = "是否固定")
    private String affix;

    @Schema(description = "显示图标")
    private String icon;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "是否隐藏面包屑")
    private byte hiddenBreadcrumb;

    @Schema(description = "激活状态")
    private byte active;

    @Schema(description = "颜色")
    private String color;

    @Schema(description = "是否整页打开路由")
    private String fullpage;

    @Schema(description = "路由的角色")
    private String role;

    @Schema(description = "标签")
    private String tag;
}
