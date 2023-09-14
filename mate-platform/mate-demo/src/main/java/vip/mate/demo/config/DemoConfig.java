package vip.mate.demo.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 演示模块相关配置
 *
 * @author matevip
 * @date 2023/9/14
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 **/
@Configuration
public class DemoConfig {

    /**
     * 配置文档分组
     */
    @Bean
    public GroupedOpenApi demoApi() {
        String[] paths = {"/**"};
        String[] packagedToMatch = {"vip.mate.demo"};
        return GroupedOpenApi.builder().group("3.演示模块")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }
}
