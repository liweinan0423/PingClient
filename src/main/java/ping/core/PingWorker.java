package ping.core;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PingWorker implements Runnable {

    private static final Logger logger = Logger.getLogger("PingWorker");

    private boolean started = false;

    public void setStarted(boolean started) {
        this.started = started;
    }

    private Server server;

    public PingWorker(Server server) {
        this.server = server;
    }


    public void run() {
        synchronized (server) {

            while (started) {
                Ping ping = new Ping();
                long start = System.currentTimeMillis();
                boolean reachable = ping.isReachable(server.getAddress());
                long end = System.currentTimeMillis();
                PingResult pingResult = new PingResult(server, reachable, (int) (end - start), new Date());
                server.updateStatus(pingResult);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.log(Level.WARNING, "",  e);
                }
            }
        }
    }
}