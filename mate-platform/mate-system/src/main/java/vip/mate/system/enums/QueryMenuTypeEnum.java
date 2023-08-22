package vip.mate.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 查询菜单类型
 * <p>
 * 0：菜单 1：按钮  2：IFrame 3: 外链 4: 除按钮外的全部 -1：全部
 * </p>
 *
 * @author matevip
 */
@Getter
@AllArgsConstructor
public enum QueryMenuTypeEnum {

    MENU(0),
    BUTTON(1),
    IFRAME(2),
    LINK(3),
    EXCEPT_BUTTON(4),
    ALL(-1);

    private final Integer value;
}
