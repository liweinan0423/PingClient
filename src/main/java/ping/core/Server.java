package ping.core;

public class Server {

    private String name;

    private String address;

    private PingStatus status;

    private PingWorker worker = new PingWorker();

    public PingWorker getWorker() {
        return worker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PingStatus getStatus() {
        return status;
    }

    public void updateStatus(PingResult result) {
        if (status == null) {
            status = new PingStatus(this);
        }

        if (result.isSuccess()) {

            status.setStatus(PingStatus.NORMAL);

            status.setSuccessNum(status.getSuccessNum() + 1);

            status.setAverageLatency((status.getAverageLatency() * (status.getSuccessNum() - 1) + result.getLatency()) / status.getSuccessNum());


        } else {
            status.setStatus(PingStatus.EXCEPTION);
            status.setFaildedNum(status.getFaildedNum() + 1);
        }
    }
}