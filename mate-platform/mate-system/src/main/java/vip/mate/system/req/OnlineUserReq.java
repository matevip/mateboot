package vip.mate.system.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.mybatis.req.PageReq;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 在线用户查询条件
 *
 * @author matevip
 * @since 2023/9/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Schema(description = "在线用户")
public class OnlineUserReq extends PageReq {
    private static final long serialVersionUID = 1L;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "登录时间")
    private List<Date> loginTime;
}
