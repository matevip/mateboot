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
* 部门
* </p>
*
* @author matevip
* @since 2023-08-24
*/
@Data
@Accessors(chain = true)
@TableName("mate_sys_dept")
@Schema(name = "SysDept对象", description = "部门")
public class SysDept extends MateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "上级ID")
    @TableField("pid")
    private Long pid;

    @Schema(description = "部门名称")
    @TableField("name")
    private String name;

    @Schema(description = "部门备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "部门负责人")
    @TableField("owner_user_id")
    private Long ownerUserId;

    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "版本号")
    @TableField("version")
    private Integer version;



}
