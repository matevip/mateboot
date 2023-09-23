package vip.mate.test;

import io.netty.util.NetUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vip.mate.core.common.utils.IpUtils;
import vip.mate.core.common.utils.ServletUtils;

import java.net.Inet4Address;

@SpringBootTest
public class IpParseTests {

    @Test
    public void ipTest() throws Exception {
        String ip = IpUtils.getIpAddr(ServletUtils.getHttpServletRequest());
        System.out.println(ip);
        System.out.println(IpUtils.getCityInfo(ip));
    }
}
