package vip.mate.core.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 *
 * @author matevip
 * @date 2023/8/13
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    /**
     * 系统基础配置
     */
    private final MateConfig mateConfig;

//    @Bean
//    public GroupedOpenApi userApi() {
//        String[] paths = {"/**"};
//        String[] packagedToMatch = {"vip.mate.system"};
//        return GroupedOpenApi.builder().group("1.系统模块")
//                .pathsToMatch(paths)
//                .packagesToScan(packagedToMatch).build();
//    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("迈特云");

        return new OpenAPI().info(new Info()
                .title(mateConfig.getName() + "多租户管理系统_接口文档")
                .description("用于管理集团旗下公司的人员信息,具体包括XXX,XXX模块")
                .contact(contact)
                .version("版本号: " + mateConfig.getVersion())
                .termsOfService("https://mate.vip")
                .license(new License().name("MIT")
                        .url("https://mate.vip")));
    }
}
