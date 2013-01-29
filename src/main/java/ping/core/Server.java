package ping.core;

public class Server {

    private String id;

    private String name;

    private String address;

    private PingStatus status;

    private PingWorker worker = new PingWorker(this);

    public PingWorker getWorker() {
        return worker;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return name + "[" + address + "]: " + this.status.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Server server = (Server) o;

        if (id != null ? !id.equals(server.id) : server.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}