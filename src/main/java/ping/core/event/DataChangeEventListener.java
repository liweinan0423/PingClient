package ping.core.event;


import ping.core.Server;

public interface DataChangeEventListener {

    public void onServerCreate(Server server);

    public void onServerRemove(Server server);

    public void onServerUpdate(Server server);

}
