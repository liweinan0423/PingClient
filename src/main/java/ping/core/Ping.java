package ping.core;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

public class Ping {

    private static final Log logger = LogFactory.getLog(Ping.class);
    public static final String OS_NAME_PROPERTY = "os.name";

    public boolean isReachable(String server) {
        Runtime runtime = Runtime.getRuntime();
        if (server == null) {
            throw new IllegalArgumentException("Server address cannot be null");
        }
        try {
            Process process = execByOS(server, runtime);
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

    private Process execByOS(String server, Runtime runtime) throws IOException {
        String osName = System.getProperty(OS_NAME_PROPERTY);
        String commandPrefix = null;
        if (osName.startsWith("Win")) {
            commandPrefix = "ping -n 1 ";
        } else {
            commandPrefix = "ping -c 1 ";
        }
        return runtime.exec(commandPrefix + server);
    }

}
