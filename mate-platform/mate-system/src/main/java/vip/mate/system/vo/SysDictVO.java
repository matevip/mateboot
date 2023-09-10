package vip.mate.system.vo;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典类型VO
 * </p>
 *
 * @author matevip
 * @since 2023-09-03
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysDictVO对象", description = "字典类型")
public class SysDictVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "字典名称")
    private String dictName;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态  0：停用   1：正常")
    private Byte status;

    @Schema(description = "创建时间")
    private Date createTime;
}
