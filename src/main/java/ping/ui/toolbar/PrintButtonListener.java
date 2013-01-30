package ping.ui.toolbar;

import ping.ui.AppController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

public class PrintButtonListener implements ActionListener {

    private AppController app;

    public PrintButtonListener(AppController app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            app.getServerTable().print();
        } catch (PrinterException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}