package ping.ui.table;

import ping.core.Server;
import ping.core.ServerRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;


public class ServerTable extends JTable {


    public ServerTable(ServerRepository repository) {
        List<Server> serverList = repository.findAllServers();
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("服务器");
        columnNames.add("地址");
        columnNames.add("延迟(ms)");
        columnNames.add("状态");

        Vector data = new Vector();

        for (Server server : serverList) {
            Vector serverVector = new Vector();
            serverVector.add(server.getName());
            serverVector.add(server.getAddress());
            serverVector.add(server.getLatency());
            serverVector.add(server.getStatus());

            data.add(serverVector);
        }


        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        this.setModel(tableModel);
    }
}