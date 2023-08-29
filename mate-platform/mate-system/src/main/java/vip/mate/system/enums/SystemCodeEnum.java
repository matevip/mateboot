package vip.mate.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vip.mate.core.common.enums.ResultCode;

@Getter
@AllArgsConstructor
public enum SystemCodeEnum implements ResultCode {

    MENU_NAME_EXIST(500, "菜单别名已存在"),
    USERNAME_EXIST(500, "用户名已存在"),
    MOBILE_EXIST(500, "手机号已存在"),
    SUB_MENU_DEL_ERROR(500, "该菜单存在子菜单不能删除"),
    USER_ID_NULL_ERROR(500, "用户ID不能为空");

    private final int code;
    private final String msg;
}
