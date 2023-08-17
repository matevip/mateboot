package vip.mate.core.mybatis.annotation;

import java.lang.annotation.*;

/**
 * todo
 *
 * @author matevip
 * @since 2023/8/13
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermission {
    DataColumn[] value();
}
