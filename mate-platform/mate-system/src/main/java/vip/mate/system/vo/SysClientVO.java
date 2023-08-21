package vip.mate.system.vo;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统授权表VO
 * </p>
 *
 * @author matevip
 * @since 2023-08-21
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysClientVO对象", description = "系统授权表")
public class SysClientVO {

    @Schema(description = "客户端id")
    private String clientId;

    @Schema(description = "客户端key")
    private String clientKey;

    @Schema(description = "客户端秘钥")
    private String clientSecret;

    @Schema(description = "授权类型")
    private String grantType;

    @Schema(description = "设备类型")
    private String deviceType;

    @Schema(description = "token活跃超时时间")
    private Integer activeTimeout;

    @Schema(description = "token固定超时")
    private Integer timeout;

    @Schema(description = "状态（0正常 1停用）")
    private String status;

}
