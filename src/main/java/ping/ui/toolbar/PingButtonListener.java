package ping.ui.toolbar;

import ping.core.Server;
import ping.ui.AppController;
import ping.ui.table.ServerTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class PingButtonListener implements ActionListener {

    private AppController app;

    public PingButtonListener(AppController app) {

        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ServerTableModel model = app.getServerTable().getServerTableModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            Server server = ServerTableModel.extractServer((Vector) model.getDataVector().get(i));
            new PingThread(server, model).start();
        }
    }
}