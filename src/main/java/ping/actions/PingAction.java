package ping.actions;

import ping.core.Server;
import ping.ui.AppController;
import ping.ui.table.ServerTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class PingAction implements ActionListener {

    private AppController app;


    private JButton button;

    public PingAction(AppController app, JButton button) {

        this.app = app;
        this.button = button;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ServerTableModel model = app.getServerTable().getServerTableModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            Server server = ServerTableModel.extractServer((Vector) model.getDataVector().get(i));
            Thread thread = new PingThread(server, model);
            thread.start();
        }
    }
}