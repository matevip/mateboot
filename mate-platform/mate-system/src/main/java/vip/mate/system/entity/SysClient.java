package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import vip.mate.core.mybatis.entity.MateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 系统授权表
* </p>
*
* @author matevip
* @since 2023-08-21
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_client")
@Schema(name = "SysClient对象", description = "系统授权表")
public class SysClient extends MateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "客户端id")
    @TableField("client_id")
    private String clientId;

    @Schema(description = "客户端key")
    @TableField("client_key")
    private String clientKey;

    @Schema(description = "客户端秘钥")
    @TableField("client_secret")
    private String clientSecret;

    @Schema(description = "授权类型")
    @TableField("grant_type")
    private String grantType;

    @Schema(description = "设备类型")
    @TableField("device_type")
    private String deviceType;

    @Schema(description = "token活跃超时时间")
    @TableField("active_timeout")
    private Integer activeTimeout;

    @Schema(description = "token固定超时")
    @TableField("timeout")
    private Integer timeout;

    @Schema(description = "状态（0正常 1停用）")
    @TableField("status")
    private String status;



}
