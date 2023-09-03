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
 * 字典类型Req
 * </p>
 *
 * @author matevip
 * @since 2023-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(name = "SysDictReq对象", description = "字典类型")
public class SysDictReq extends PageReq {

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

}
