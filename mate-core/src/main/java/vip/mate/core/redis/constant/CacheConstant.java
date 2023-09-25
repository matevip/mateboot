package vip.mate.core.redis.constant;

/**
 * 缓存常量管理
 *
 * @author matevip
 * @date 2023/8/21
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
public class CacheConstant {

    /**
     * 验证码Key
     */
    public static String getCaptchaKey(String key) {
        return "sys:captcha:" + key;
    }

    /**
     * accessToken Key
     */
    public static String getAccessTokenKey(String accessToken) {
        return "sys:access:" + accessToken;
    }


    /**
     * 日志Key
     */
    public static String getLogKey() {
        return "sys:log";
    }
}
