package ping.core;

import java.util.List;

public class ServerRepository {

    public Server findByName(String name) {
        return null;
    }

    public Server saveServer(Server server) {

    }

    public List<Server> findAllServers() {

    }

    public void removeServer(Server server) {

    }

    public void removeServerByName(String name) {
        removeServer(findByName(name));
    }

}