package ping.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ping {

    private static final Logger logger = Logger.getLogger("Ping");
    public static final String OS_NAME_PROPERTY = "os.name";

    public boolean isReachable(String address) {
//        try {
//            return InetAddress.getByName(address).isReachable(3000);
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            return false;
//        }
        Runtime runtime = Runtime.getRuntime();
        if (address == null) {
            throw new IllegalArgumentException("Server address cannot be null");
        }
        try {
            Process process = execByOS(address, runtime);
            int result = process.waitFor();
            return result == 0;
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return false;
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
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
