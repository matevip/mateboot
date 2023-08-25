package vip.mate.core.mybatis.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.mate.core.mybatis.req.PageReq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页响应类
 *
 * @author matevip
 * @date 2023/8/19
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class PageRes<T> extends PageReq implements Serializable {
    /**
     * 数据
     */
    @Schema(description = "数据")
    private List<T> list;
    /**
     * 总量
     */
    @Schema(description = "总记录数")
    private Long total;
    /**
     * 页码
     */
    @Schema(description = "页码")
    private Long pageNo;
    /**
     * 每页条数
     */
    @Schema(description = "每页条数")
    private Long pageSize;


    public PageRes() {
    }

    public PageRes(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public PageRes(List<T> list, Long total, PageReq req) {
        this.list = list;
        this.total = total;
        this.pageNo = req.getPageNo();
        this.pageSize = req.getPageSize();
    }

    public PageRes(List<T> list, Long total, Long pageNo, Long pageSize) {
        this.list = list;
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageRes(Long total) {
        this.list = new ArrayList<>();
        this.total = total;
    }

    public static <T> PageRes<T> empty() {
        return new PageRes<>(0L);
    }

    public static <T> PageRes<T> empty(Long total) {
        return new PageRes<>(total);
    }
}
