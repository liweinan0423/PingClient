package ping.ui.table;

import ping.core.ServerRepository;
import ping.ui.AppController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ServerTable extends JTable {


    private final AppController app;

    public ServerTable(ServerRepository repository, AppController app) {
        this.app = app;


        DefaultTableModel tableModel = new ServerTableModel(repository, this.app);

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