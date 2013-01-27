package ping.test;


import org.junit.Test;
import ping.ui.MainFame;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import static junit.framework.Assert.assertTrue;

public class PintTest {

    @Test
    public void test_ping() throws IOException {
        InetAddress address = InetAddress.getByName("www.sina.com");

        boolean reachable = address.isReachable(10000);
        assertTrue(reachable);

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            System.out.println(networkInterface);
        }
    }

    @Test
    public void test_viewport() {
        MainFame mainFame = new MainFame();
    }
}