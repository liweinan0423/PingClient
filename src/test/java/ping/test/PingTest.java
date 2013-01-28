package ping.test;

import org.junit.Test;
import ping.core.Ping;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PingTest {

    private final static String REACHABLE_IP_ADDRESS = "74.125.128.105";   //www.google.com
    private final static String REACHABLE_SERVER_HOST = "www.sina.com";
    private final static String UNREACHABLE_IP_ADDRESS = "1.2.3.4";
    private final static String UNREACHABLE_SERVER_HOST = "www.unknown.com";


    @Test
    public void test_ping_reachable_server_by_ip_address() {
        Ping ping = new Ping();
        assertTrue(ping.isReachable(REACHABLE_IP_ADDRESS));
    }

    @Test
    public void test_ping_reachable_server_by_hostname() {
        Ping ping = new Ping();
        assertTrue(ping.isReachable(REACHABLE_SERVER_HOST));
    }

    @Test
    public void test_ping_unreachable_server_by_ip_address() {
        Ping ping = new Ping();
        assertFalse(ping.isReachable(UNREACHABLE_IP_ADDRESS));
    }

    @Test
    public void test_ping_unreachable_server_by_hostname() {
        Ping ping = new Ping();
        assertFalse(ping.isReachable(UNREACHABLE_SERVER_HOST));
    }

}
