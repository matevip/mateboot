package vip.mate.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vip.mate.core.common.utils.IpUtils;
import vip.mate.core.common.utils.ServletUtils;

@SpringBootTest
public class IpParseTests {

    @Test
    public void ipTest() throws Exception {
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        System.out.println(ip);
        System.out.println(IpUtils.getCityInfo(ip));
    }
}
