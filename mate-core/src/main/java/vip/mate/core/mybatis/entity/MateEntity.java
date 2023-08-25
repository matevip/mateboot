package vip.mate.core.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fhs.core.trans.vo.TransPojo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Entity的基础类
 *
 * @author matevip
 * @date 2023/8/19
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Getter
@Setter
public abstract class MateEntity implements TransPojo {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @Schema(description = "ID")
    private Long id;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建者ID")
    private Long  createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新者ID")
    private Long  updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 删除标记
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "删除标识")
    private Integer deleted;
}
