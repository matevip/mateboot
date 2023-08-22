package vip.mate.system.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 菜单类型枚举
 *
 * @author matevip 7333791@qq.com
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    /**
     * 菜单
     */
    MENU(0, "menu"),
    /**
     * 按钮
     */
    BUTTON(1, "button"),
    /**
     * IFrame
     */
    IFRAME(2, "iframe"),
    /**
     * 外链
     */
    LINK(3, "link");

    private final int value;

    private final String code;

    private static final Map<String, MenuTypeEnum> map = new HashMap<>();

    static {
        for (MenuTypeEnum t : EnumSet.allOf(MenuTypeEnum.class)) {
            map.put(StrUtil.toString(t.getValue()), t);
        }
    }

    public static MenuTypeEnum getCode(int value) {
        return map.get(StrUtil.toString(value));
    }
}
