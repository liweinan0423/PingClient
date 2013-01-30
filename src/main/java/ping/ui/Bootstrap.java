package ping.ui;


import ping.core.ServerRepository;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
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