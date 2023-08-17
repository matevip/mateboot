package vip.mate.core.mybatis.interceptor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据范围
 *
 * @author matevip
 * @date 2023/8/13
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Data
@AllArgsConstructor
public class DataScope {
    private String sqlFilter;
}
