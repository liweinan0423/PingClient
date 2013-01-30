package ping.ui.toolbar;

import ping.ui.AppController;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.*;
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

            PrintService service = PrintServiceLookup.lookupDefaultPrintService();

            app.getServerTable().print(/*
                    JTable.PrintMode.NORMAL,
                    null,
                    null,
                    true,
                    new HashPrintRequestAttributeSet(),
                    true,
                    service*/);
        } catch (PrinterException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}