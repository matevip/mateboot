package vip.mate.core.common.context;

import vip.mate.core.common.req.UserVO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户信息上下文
 *
 * @author matevip
 * @date 2023/9/25
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
public class UserInfoContextHolder {

    private static final Map<Integer, UserVO> USER_INFO = new ConcurrentHashMap<>();

    /**
     * 设置用户信息
     */
    public static void set(Long userId, UserVO userVO) {
        USER_INFO.put(userId.intValue(), userVO);
    }

    /**
     * 获取用户信息
     */
    public static UserVO get(Long userId) {
        return USER_INFO.get(userId.intValue());
    }

    /**
     * 删除用户信息
     */
    public static void remove(Long userId) {
        USER_INFO.remove(userId.intValue());
    }

    /**
     * 刷新用户信息
     */
    public static void refresh(Long userId, UserVO userVO) {
        set(userId, userVO);
    }
}
