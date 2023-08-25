package vip.mate.core.common.enums;

/**
 * 错误编码
 *
 * @author matevip
 * @date 2023/8/25
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
public interface ResultCode {


    /**
     * 系统响应码
     *
     * @return code
     */
    public int getCode();

    /**
     * 默认系统响应提示，code=0时此处为空
     *
     * @return msg
     */
    public String getMsg();
}
