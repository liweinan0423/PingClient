package ping.core;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ping {

    private static final Log logger = LogFactory.getLog(Ping.class);

    private static final int DEFAULT_TIMEOUT = 10000;
    public Ping() {
        setTimeout(DEFAULT_TIMEOUT);
    }

    private int timeout;


    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isReachable(String server) {
        try {
            InetAddress address = InetAddress.getByName(server);
            boolean result = address.isReachable(timeout);
            logger.debug("ping[" + server + "]result:" + result);
            return result;
        } catch (UnknownHostException e) {
            logger.debug("", e);
            return false;
        } catch (IOException e) {
            logger.debug("", e);
            return false;
        }
    }
}
