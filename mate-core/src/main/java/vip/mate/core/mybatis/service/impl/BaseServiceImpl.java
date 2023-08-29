package vip.mate.core.mybatis.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.core.mybatis.req.PageReq;
import vip.mate.core.mybatis.service.BaseService;

/**
 * 通用serviceImpl
 *
 * @author matevip
 * @date 2023/8/29
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    private final static String ASC = "ascending";

    /**
     * 分页对象增加排序功能
     *
     * @param req 分页参数
     */
    protected IPage<T> getPage(PageReq req) {
        Page<T> page = new Page<>(req.getPageNo(), req.getPageSize());
        // 驼峰转成下划线
        String propStr = StrUtil.toUnderlineCase(req.getProp());
        // 排序
        if (StringUtils.isNotBlank(req.getProp())) {
            if (StringUtils.equals(req.getOrder(), ASC)) {
                return page.addOrder(OrderItem.asc(propStr));
            } else {
                return page.addOrder(OrderItem.desc(propStr));
            }
        }
        return page;
    }
}
