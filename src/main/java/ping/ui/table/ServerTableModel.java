package ping.ui.table;

import ping.core.Server;
import ping.core.ServerRepository;
import ping.ui.AppController;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class ServerTableModel extends DefaultTableModel {


    private ServerRepository repository;
    private final AppController app;

    public ServerTableModel(ServerRepository repository, AppController app) {
        this.repository = repository;
        this.app = app;

        List<Server> serverList = repository.findAllServers();
        Vector<String> columnNames = getColumnNamesVector();

        Vector data = new Vector();

        for (Server server : serverList) {


            data.add(getServerDataVector(server));
        }

        this.setDataVector(data, columnNames);


        this.addTableModelListener(new ServerTableModelListener(this.app));
    }



    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 3 && columnIndex != 4;
    }

    public static Vector<String> getColumnNamesVector() {
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("id");
        columnNames.add("服务器");
        columnNames.add("地址");
        columnNames.add("延迟(ms)");
        columnNames.add("状态");
        return columnNames;
    }

    public static Vector getServerDataVector(Server server) {
        Vector serverVector = new Vector();

        serverVector.add(server.getId());
        serverVector.add(server.getName());
        serverVector.add(server.getAddress());
        serverVector.add(server.getLatency());
        serverVector.add(server.getStatus());

        return serverVector;
    }

    public static Server extractServer(Vector vector) {
        Server server = new Server();
        server.setId(vector.get(0).toString());
        server.setName(vector.get(1).toString());
        server.setAddress(vector.get(2).toString());
        server.setLatency(Integer.valueOf(vector.get(3).toString()));
        server.setStatus(Integer.valueOf(vector.get(4).toString()));

        return server;
    }

    public ServerRepository getRepository() {
        return repository;
    }

    public void update(Server server) {
        for (int i = 0; i < this.getRowCount(); i++) {
            if (this.getValueAt(i, 0) == server.getId()) {
                this.setValueAt(server.getName(), i, 1);
                this.setValueAt(server.getAddress(), i, 2);
                this.setValueAt(server.getLatency(), i, 3);
                this.setValueAt(server.getStatus(), i ,4);
            }
        }
    }
}
