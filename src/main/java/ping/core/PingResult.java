package ping.core;

import java.util.Date;

public class PingResult {

    private Server server;
    private boolean success;
    private int latency;
    private Date executeDate;

    public PingResult(Server server, boolean success, int latency, Date executeDate) {
        this.server = server;
        this.success = success;
        this.latency = latency;
        this.executeDate = executeDate;
    }

    public Server getServer() {
        return server;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getLatency() {
        return latency;
    }

    public Date getExecuteDate() {
        return executeDate;
    }
}