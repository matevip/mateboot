package vip.mate.core.mybatis.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 多租户的基础类
 *
 * @author matevip
 * @date 2023/8/19
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class TenantEntity extends MateEntity {

    /**
     * 多租户编号
     */
    @Schema(description = "租户ID")
    private String tenantId;
}
