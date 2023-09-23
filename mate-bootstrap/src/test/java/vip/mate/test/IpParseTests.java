package vip.mate.test;

import io.netty.util.NetUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vip.mate.core.common.utils.IpUtils;

import java.net.Inet4Address;

@SpringBootTest
public class IpParseTests {

    @Test
    public void ipTest() throws Exception {
        Inet4Address localhost4 = NetUtil.LOCALHOST4;
        String ip = localhost4.getHostAddress();
        ip = "172.71.218.175";
        System.out.println(ip);
        System.out.println(IpUtils.getCityInfo(ip));
    }
}
