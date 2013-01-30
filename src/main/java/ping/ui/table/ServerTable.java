package ping.ui.table;

import ping.core.Server;
import ping.core.ServerRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;


public class ServerTable extends JTable {


    private final ServerRepository serverRepository;

    public ServerTable(ServerRepository repository) {

        this.serverRepository = repository;

        List<Server> serverList = repository.findAllServers();
        Vector<String> columnNames = getColumnNamesVector();

        Vector data = new Vector();

        for (Server server : serverList) {


            data.add(getServerDataVector(server));
        }


        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        ServerDataListener listener = new ServerDataListener(tableModel);
        repository.addDataChangeEventListener(listener);

        this.setModel(tableModel);

        this.getColumnModel().getColumn(0).setMaxWidth(0);
        this.getColumnModel().getColumn(0).setMinWidth(0);
        this.getColumnModel().getColumn(0).setPreferredWidth(0);

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
}