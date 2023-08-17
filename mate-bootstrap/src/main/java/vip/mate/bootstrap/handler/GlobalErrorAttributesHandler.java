package vip.mate.bootstrap.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import vip.mate.core.common.exception.ServerException;
import vip.mate.core.common.response.Result;
import vip.mate.core.common.utils.HttpContextUtils;

import java.util.Map;

/**
 * 将未知错误异常，输出格式重写为我们熟悉的响应格式
 *
 * @author matevip
 * @date 2023/8/13
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 **/
@Component
public class GlobalErrorAttributesHandler extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions attributeOptions) {
        // 获取spring默认的返回内容
        Map<String, Object> defaultErrorAttributes = super.getErrorAttributes(webRequest, attributeOptions);

        // 获取其状态码
        Object status = defaultErrorAttributes.get("status");
        if (ObjectUtil.isNotEmpty(status)) {
            // 如果其为404，则处理
            if (HttpStatus.HTTP_NOT_FOUND == Convert.toInt(status)) {
                Object path = defaultErrorAttributes.get("path");
                if(ObjectUtil.isNotEmpty(path)) {
                    return BeanUtil.beanToMap(Result.get(HttpStatus.HTTP_NOT_FOUND, "路径不存在，请求地址：" +
                            Convert.toStr(path), null));
                } else {
                    return BeanUtil.beanToMap(Result.get(HttpStatus.HTTP_NOT_FOUND, "路径不存在", null));
                }
            } else {
                return BeanUtil.beanToMap(Result.get(HttpStatus.HTTP_INTERNAL_ERROR, "服务器异常，请求地址：" +
                        HttpContextUtils.getHttpServletRequest().getRequestURL(), null));
            }
        }

        // ServerException，则按ServerException响应的内容进行返回
        Throwable throwable = this.getError(webRequest);
        if (ObjectUtil.isNotEmpty(throwable)) {
            if (throwable instanceof ServerException) {
                ServerException serverException = (ServerException) throwable;

                return BeanUtil.beanToMap(Result.error(serverException.getMsg()));
            } else {
                return BeanUtil.beanToMap(Result.get(HttpStatus.HTTP_INTERNAL_ERROR, "服务器异常，请求地址：" +
                        HttpContextUtils.getHttpServletRequest().getRequestURL(), null));
            }
        } else {
            // throwable为空，则直接返回默认异常
            return BeanUtil.beanToMap(Result.error());
        }
    }
}
