package vip.mate.system.req;

import vip.mate.core.mybatis.req.PageReq;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 部门Req
 * </p>
 *
 * @author matevip
 * @since 2023-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(name = "SysDeptReq对象", description = "部门")
public class SysDeptReq extends PageReq {

    @Schema(description = "上级ID")
    private Long pid;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "部门备注")
    private String remark;

    @Schema(description = "部门负责人")
    private Long ownerUserId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "版本号")
    private Integer version;

}
