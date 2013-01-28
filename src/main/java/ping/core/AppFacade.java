package ping.core;

import java.util.List;

public class AppFacade {

    private ServerRepository serverRepository;


    public AppFacade(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    List<Server> findAllServers() {
        return serverRepository.findAllServers();
    }

    public void start() {
        List<Server> allServers = findAllServers();




    }

}