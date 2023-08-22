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
* 角色管理
* </p>
*
* @author matevip
* @since 2023-08-22
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_role")
@Schema(name = "SysRole对象", description = "角色管理")
public class SysRole extends MateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "角色名称")
    @TableField("name")
    private String name;

    @Schema(description = "编码")
    @TableField("code")
    private String code;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "数据范围  0：全部数据  1：本部门及子部门数据  2：本部门数据  3：本人数据  4：自定义数据")
    @TableField("data_scope")
    private Byte dataScope;

    @Schema(description = "机构ID")
    @TableField("org_id")
    private Long orgId;

    @Schema(description = "版本号")
    @TableField("version")
    private Integer version;

    @Schema(description = "状态")
    @TableField("status")
    private Byte status;



}
