package vip.mate.bootstrap.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主应用配置类
 *
 * @author matevip
 * @date 2023/8/20
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 **/
@Configuration
public class BootstrapConfig {

    /**
     * 指定文档名称
     *
     * @return
     */
    @Bean
    public GroupedOpenApi bootstrapApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"vip.mate.bootstrap"};
        return GroupedOpenApi.builder().group("1.启动模块")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }
}
