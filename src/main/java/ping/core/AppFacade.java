package ping.core;

import java.util.List;

public class AppFacade {

    private ServerRepository serverRepository;


    public AppFacade(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public List<Server> findAllServers() {
        return serverRepository.findAllServers();
    }

    public void start() {
        List<Server> allServers = findAllServers();

        for (Server server : allServers) {
            server.getWorker().setStarted(true);
            new Thread(server.getWorker()).start();
        }
    }

    public void stop() {
        List<Server> allServers = findAllServers();

        for (Server server : allServers) {
            server.getWorker().setStarted(false);
        }
    }
}