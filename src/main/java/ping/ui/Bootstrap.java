package ping.ui;


import ping.core.ServerRepository;

import javax.swing.*;

public class Bootstrap {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        ServerRepository repository = new ServerRepository(System.getProperty("user.home") + "/servers.xml");
        AppController app = new AppController(repository);
        app.init();
    }
}