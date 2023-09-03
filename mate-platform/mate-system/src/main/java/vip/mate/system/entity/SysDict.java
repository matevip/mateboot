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
* 字典类型
* </p>
*
* @author matevip
* @since 2023-09-03
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_dict")
@Schema(name = "SysDict对象", description = "字典类型")
public class SysDict extends MateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "字典类型")
    @TableField("dict_type")
    private String dictType;

    @Schema(description = "字典名称")
    @TableField("dict_name")
    private String dictName;

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
