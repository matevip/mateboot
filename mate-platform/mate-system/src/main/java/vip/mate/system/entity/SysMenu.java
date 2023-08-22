package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import vip.mate.core.mybatis.entity.MateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 菜单数据
* </p>
*
* @author matevip
* @since 2023-08-22
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_menu")
@Schema(name = "SysMenu对象", description = "菜单数据")
public class SysMenu extends MateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "上级ID，一级菜单为0")
    @TableField("pid")
    private Long pid;

    @Schema(description = "别名")
    @TableField("name")
    private String name;

    @Schema(description = "菜单名称")
    @TableField("title")
    private String title;

    @Schema(description = "菜单URL")
    @TableField("path")
    private String path;

    @Schema(description = "组件")
    @TableField("component")
    private String component;

    @Schema(description = "授权标识")
    @TableField("authority")
    private String authority;

    @Schema(description = "类型   0：菜单   1：按钮   2：接口")
    @TableField("type")
    private Byte type;

    @Schema(description = "打开方式   0：内部   1：外部")
    @TableField("open_style")
    private Byte openStyle;

    @Schema(description = "菜单图标")
    @TableField("icon")
    private String icon;

    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "是否固定")
    @TableField("affix")
    private Byte affix;

    @Schema(description = "标签")
    @TableField("tag")
    private String tag;

    @Schema(description = "隐藏菜单 0：否 1：是")
    @TableField("hidden")
    private Byte hidden;

    @Schema(description = "隐藏面包屑 0：否 1：是")
    @TableField("hidden_breadcrumb")
    private Byte hiddenBreadcrumb;

    @Schema(description = "版本号")
    @TableField("version")
    private Integer version;



}
