package ping.ui.table;

import ping.core.ServerRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ServerTable extends JTable {


    public ServerTable(ServerRepository repository) {


        DefaultTableModel tableModel = new ServerTableModel(repository);

        ServerDataListener listener = new ServerDataListener(tableModel);
        repository.addDataChangeEventListener(listener);

        this.setModel(tableModel);

        this.getColumnModel().getColumn(0).setMaxWidth(0);
        this.getColumnModel().getColumn(0).setMinWidth(0);
        this.getColumnModel().getColumn(0).setPreferredWidth(0);

        this.getColumnModel().getColumn(4).setCellRenderer(new ServerStatusCellRenderer());

    }

    public ServerTableModel getServerTableModel() {
        return (ServerTableModel) this.getModel();
    }
}