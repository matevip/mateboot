package vip.mate.system.config;

import cn.dev33.satoken.stp.StpInterface;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.mate.system.service.impl.SaInterfaceImpl;

/**
 * 系统功能相关配置
 *
 * @author matevip
 * @date 2023/8/13
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Configuration
public class SystemConfig {

    /**
     * 指定文档名称
     */
    @Bean
    public GroupedOpenApi systemApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"vip.mate.system"};
        return GroupedOpenApi.builder().group("2.系统模块")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }

    /**
     * sa-token 权限管理实现类
     */
    @Bean
    public StpInterface stpInterface() {
        return new SaInterfaceImpl();
    }
}
