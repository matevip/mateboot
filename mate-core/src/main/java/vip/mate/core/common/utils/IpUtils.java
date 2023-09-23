package vip.mate.core.common.utils;

import cn.hutool.core.net.NetUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import net.dreamlu.mica.ip2region.core.IpInfo;
import vip.mate.core.common.config.MateConfig;

/**
 * IP工具类
 *
 * @author matevip
 * @date 2023/8/22
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IpUtils {

    /**
     * 太平洋网开放 API：查询 IP 归属地
     */
    private static final String IP_URL = "https://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";

    public static String getCityInfo(String ip) {
        if (MateConfig.IP_ADDR_LOCAL_PARSE_ENABLED) {
            return getLocalCityInfo(ip);
        } else {
            return getHttpCityInfo(ip);
        }
    }

    /**
     * 通过太平洋网开放 API 查询 IP 归属地
     *
     * @param ip IP地址
     * @return 归属地
     */
    public static String getHttpCityInfo(String ip) {
        if (isInnerIp(ip)) {
            return "内网IP";
        }
        String api = String.format(IP_URL, ip);
        JSONObject object = JSONUtil.parseObj(HttpUtil.get(api));
        return object.get("addr", String.class);
    }

    /**
     * 通过本地查询 IP 归属地
     *
     * @param ip IP地址
     * @return 归属地
     */
    public static String getLocalCityInfo(String ip) {
        if (isInnerIp(ip)) {
            return "内网IP";
        }
        Ip2regionSearcher ip2regionSearcher = SpringUtil.getBean(Ip2regionSearcher.class);
        IpInfo ipInfo = ip2regionSearcher.memorySearch(ip);
        if (null != ipInfo) {
            return ipInfo.getAddress();
        }
        return null;
    }

    /**
     * 判断是否内网IP
     *
     * @param ip IP地址
     * @return 是否内网IP
     */
    public static boolean isInnerIp(String ip) {
        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : HtmlUtil.cleanHtmlTag(ip);
        return NetUtil.isInnerIP(ip);
    }

}
