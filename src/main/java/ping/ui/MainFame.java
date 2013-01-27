package ping.ui;

import ping.ui.list.PingInfoList;
import ping.ui.menu.PMenuBar;
import ping.ui.statusbar.StatusBar;
import ping.ui.toolbar.PToolBar;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class MainFame extends JFrame {

    private PMenuBar menuBar;
    private PToolBar toolBar;
    private PingInfoList pingInfoList;
    private StatusBar statusBar;

    public MainFame() {
        super();

        menuBar = new PMenuBar();
        toolBar = new PToolBar();
        pingInfoList = new PingInfoList();
        statusBar = new StatusBar();

        Container container = new Container();
        container.setLayout(new BorderLayout());
        container.add(BorderLayout.NORTH, toolBar);

        //put JTable into a JScrollPane to show the headers of the table;
        container.add(BorderLayout.CENTER, new JScrollPane(pingInfoList));
        container.add(BorderLayout.SOUTH, statusBar);




        this.setJMenuBar(menuBar);
        this.setContentPane(container);
        this.pack();
        this.setVisible(true);

    }
}