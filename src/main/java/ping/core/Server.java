package ping.core;


public class Server {

    private String name;

    private String address;

    private PingStatus status;

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
}