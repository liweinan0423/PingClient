package ping.ui;

import ping.core.ServerRepository;
import ping.ui.menu.PMenuBar;
import ping.ui.statusbar.StatusBar;
import ping.ui.table.ServerTable;
import ping.ui.toolbar.*;

import javax.swing.*;
import java.awt.*;

public class AppController {

    private PMenuBar menuBar;
    private PToolBar toolBar;
    private ServerTable serverTable;
    private StatusBar statusBar;
    private ServerRepository serverRepository;
    private JFrame frame;

    public AppController(ServerRepository repository) {
        this.serverRepository = repository;
    }

    public void init() {

        menuBar = new PMenuBar();

        toolBar = new PToolBar();
        toolBar.getAddButton().addActionListener(new AddButtonListener(this));
        toolBar.getRemoveButton().addActionListener(new RemoveButtonListener(this));
        toolBar.getStartPingBtn().addActionListener(new PingButtonListener(this));
        toolBar.getPrintButton().addActionListener(new PrintButtonListener(this));

        serverTable = new ServerTable(serverRepository);
        statusBar = new StatusBar();

        Container container = new Container();
        container.setLayout(new BorderLayout());
        container.add(BorderLayout.NORTH, toolBar);

        //put JTable into a JScrollPane to show the headers of the table;
        container.add(BorderLayout.CENTER, new JScrollPane(serverTable));
        container.add(BorderLayout.SOUTH, statusBar);

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);

        frame.setJMenuBar(menuBar);
        frame.setContentPane(container);
        frame.pack();
        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    public ServerRepository getServerRepository() {
        return serverRepository;
    }

    public ServerTable getServerTable() {
        return serverTable;
    }
}