package ping.core;

public class PingStatus {

    private Server server;

    private int successNum;

    private int faildedNum;

    private int averageLatency;

    private int status;

    public PingStatus(Server server) {
        this.server = server;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getFaildedNum() {
        return faildedNum;
    }

    public void setFaildedNum(int faildedNum) {
        this.faildedNum = faildedNum;
    }

    public int getAverageLatency() {
        return averageLatency;
    }

    public void setAverageLatency(int averageLatency) {
        this.averageLatency = averageLatency;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Server getServer() {
        return server;
    }
}