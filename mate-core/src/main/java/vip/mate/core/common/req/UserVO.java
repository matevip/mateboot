package vip.mate.core.common.req;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * todo
 *
 * @author matevip
 * @since 2023/9/25
 */
@Data
@Accessors(chain = true)
@Schema(description = "用户请求对象")
public class UserVO {
    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别   0：男   1：女   2：未知")
    private Byte gender;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "部门ID")
    private Long deptId;

    @Schema(description = "超级管理员   0：否   1：是")
    private Byte superAdmin;

    @Schema(description = "状态  0：停用   1：正常")
    private Byte status;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "角色ID列表")
    private List<Long> roleIdList;

    @Schema(description = "权限ID列表")
    private List<String> permissionIdList;

    @Schema(description = "token信息")
    private SaTokenInfo tokenInfo;
}
