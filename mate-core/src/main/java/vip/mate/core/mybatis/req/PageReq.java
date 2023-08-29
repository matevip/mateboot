package vip.mate.core.mybatis.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页请求类
 *
 * @author matevip
 * @date 2023/8/19
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Getter
@Setter
public class PageReq {

    private static final Long PAGE_NO = 1L;
    private static final Long PAGE_SIZE = 10L;

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    @Schema(description = "页码")
    private Long pageNo = PAGE_NO;

    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小值为 1")
    @Max(value = 100, message = "每页条数最大值为 999")
    @Schema(description = "每页条数")
    private Long pageSize = PAGE_SIZE;

    @Schema(description = "排序字段")
    private String prop;

    @Schema(description = "排序方式")
    private String order;

    @Schema(description = "模糊查询关键词")
    private String keyword;
}
