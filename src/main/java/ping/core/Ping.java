package ping.core;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

public class Ping {

    private static final Log logger = LogFactory.getLog(Ping.class);

    public boolean isReachable(String server) {
        Runtime runtime = Runtime.getRuntime();
        if (server == null) {
            throw new IllegalArgumentException("Server address cannot be null");
        }
        try {
            Process process = runtime.exec("ping -c 1 " + server);
            int result = process.waitFor();
            return result == 0;
        } catch (IOException e) {
            logger.debug("", e);
            return false;
        } catch (InterruptedException e) {
            logger.debug("", e);
            return false;
        }
    }
}
