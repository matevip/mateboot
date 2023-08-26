package vip.mate.system.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 按钮菜单对象
 *
 * @author matevip
 * @since 2023/8/26
 */
@Data
@Accessors(chain = true)
@Schema(name = "按钮菜单")
public class ButtonMenuReq {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "上级ID")
    private Long pid;

    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "授权标识")
    private String authority;

    @Schema(description = "类型")
    private Byte type;
}
