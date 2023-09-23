package vip.mate.core.common.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Boot 基础配置
 *
 * @author matevip
 * @date 2023/8/13
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Data
@Component
@ConfigurationProperties(prefix = "mate")
public class MateConfig {
    /**
     * 项目名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;

    /**
     * 版权年份
     */
    private String copyrightYear;

    /**
     * 实例演示开关
     */
    private boolean demoEnabled;

    /**
     * 本地IP地址解析开关
     */
    private boolean localIpEnabled;

    /**
     * 是否本地解析 IP 归属地
     */
    public static final boolean IP_ADDR_LOCAL_PARSE_ENABLED;

    static {
        IP_ADDR_LOCAL_PARSE_ENABLED = Convert.toBool(SpringUtil.getProperty("mate.localIpEnabled"));
    }
}
