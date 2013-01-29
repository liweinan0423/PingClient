package ping.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ping.core.DuplicateServerDefinitionException;
import ping.core.Server;
import ping.core.ServerRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AppTest {

    private Server server1;
    private Server server2;

    private String dataFile;

    private ServerRepository repository;

    @Before
    public void setup() throws IOException {
        server1 = new Server();
        server1.setName("服务器1");
        server1.setAddress("192.168.0.1");

        server2 = new Server();
        server2.setName("服务器2");
        server2.setAddress("www.sina.com");

        dataFile = System.getProperty("user.home") + "/" + UUID.randomUUID() + "servers.xml";
        System.out.println(dataFile);

        repository = new ServerRepository(dataFile);

    }

    @After
    public void tearDown() {
        new File(dataFile).delete();
    }


    @Test
    public void test_save_and_read_server() throws DuplicateServerDefinitionException {


        repository.saveServer(server1);

        List<Server> servers = repository.findAllServers();

        assertEquals(1, servers.size());

        assertEquals(server1.getName(), servers.get(0).getName());
        assertEquals(server1.getAddress(), servers.get(0).getAddress());


    }

    @Test
    public void test_save_duplicate_server() {

        try {
            repository.saveServer(server1);
        } catch (DuplicateServerDefinitionException e) {
            fail();
        }

        try {
            repository.saveServer(server1);
        } catch (DuplicateServerDefinitionException e) {
            return;
        }
        fail();

    }

    @Test
    public void test_remove_server() throws DuplicateServerDefinitionException {
        repository.saveServer(server1);
        repository.saveServer(server2);

        repository.removeServer(server1);

        assertEquals(1, repository.findAllServers().size());

        assertEquals(server2, repository.findAllServers().get(0));

    }

    @Test
    public void test_update_server() throws DuplicateServerDefinitionException {
        repository.saveServer(server1);

        server1.setAddress("New address");
        server1.setName("New Name");

        repository.updateServer(server1);

        Server server = repository.findById(server1.getId());

        assertEquals(server.getName(), server1.getName());
        assertEquals(server.getAddress(), server1.getAddress());

    }

}