package vip.mate.core.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Http
 *
 * @author matevip
 * @date 2023/8/13
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
public class ServletUtils {

    private final static String USER_AGENT = "User-Agent";

    /**
     * 获取 HttpServletRequest 请求
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }


    /**
     * 获取 HttpServletResponse 响应
     */
    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }

        return ((ServletRequestAttributes) requestAttributes).getResponse();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取请求参数
     */
    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        Enumeration<String> parameters = request.getParameterNames();

        Map<String, String> params = new HashMap<>();
        while (parameters.hasMoreElements()) {
            String parameter = parameters.nextElement();
            String value = request.getParameter(parameter);
            if (StrUtil.isNotBlank(value)) {
                params.put(parameter, value);
            }
        }

        return params;
    }

    /**
     * 获取域名
     */
    public static String getDomain() {
        HttpServletRequest request = getRequest();

        return getDomain(request);
    }

    /**
     * 获取域名
     */
    public static String getDomain(HttpServletRequest request) {
        String domain = request.getHeader(HttpHeaders.ORIGIN);
        if (StrUtil.isBlank(domain)) {
            domain = request.getHeader(HttpHeaders.REFERER);
        }
        return StringUtils.removeEnd(domain, "/");
    }

    /**
     * 获取来源
     */
    public static String getOrigin() {
        HttpServletRequest request = getRequest();
        return request.getHeader(HttpHeaders.ORIGIN);
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取浏览器及其版本信息
     *
     * @param request 请求信息
     * @return 浏览器及其版本信息
     */
    public static String getBrowser(HttpServletRequest request) {
        if (null == request) {
            return null;
        }
        UserAgent userAgent = UserAgentUtil.parse(request.getHeader(USER_AGENT));
        return userAgent.getBrowser().getName() + " " + userAgent.getVersion();
    }

    /**
     * 获取操作系统及其版本信息
     *
     * @param request 请求信息
     * @return 操作系统及其版本信息
     */
    public static String getOs(HttpServletRequest request) {
        if (null == request) {
            return null;
        }
        UserAgent userAgent = UserAgentUtil.parse(request.getHeader(USER_AGENT));
        return userAgent.getOs().getName();
    }

}