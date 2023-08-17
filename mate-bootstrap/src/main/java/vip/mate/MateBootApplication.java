package vip.mate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Web应用启动类
 *
 * @author matevip
 * @date 2023/8/13
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@SpringBootApplication
public class MateBootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MateBootApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MateBootApplication.class);
    }
}