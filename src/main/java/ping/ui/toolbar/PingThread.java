package ping.ui.toolbar;

import ping.core.Ping;
import ping.core.Server;
import ping.ui.table.ServerTableModel;

public class PingThread extends Thread {

    private Server server;
    private ServerTableModel model;

    public PingThread(Server server, ServerTableModel model) {
        this.server = server;
        this.model = model;
    }

    @Override
    public void run() {
        synchronized (server) {
            Ping ping = new Ping();


            long start = System.currentTimeMillis();
            boolean success = ping.isReachable(server.getAddress());
            long end = System.currentTimeMillis();

            if (success) {
                server.setStatus(Server.NORMAL);
                server.setLatency((int) (end - start));

            } else {
                server.setStatus(Server.EXCEPTION);
                server.setLatency(Server.NO_LATENCY);
            }

            model.update(server);
        }
    }
}