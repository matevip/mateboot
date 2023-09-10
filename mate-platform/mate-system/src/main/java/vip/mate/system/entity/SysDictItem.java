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
* 字典项
* </p>
*
* @author matevip
* @since 2023-09-03
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_dict_item")
@Schema(name = "SysDictItem对象", description = "字典项")
public class SysDictItem extends MateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "字典类型ID")
    @TableField("dict_id")
    private Long dictId;

    @Schema(description = "字典名称")
    @TableField("dict_name")
    private String dictName;

    @Schema(description = "字典值")
    @TableField("dict_value")
    private String dictValue;

    @Schema(description = "字典样式")
    @TableField("dict_class")
    private String dictClass;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "状态  0：停用   1：正常")
    @TableField("status")
    private Byte status;



}
