package ping.ui.toolbar;

import ping.ui.AppController;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintButtonListener implements ActionListener {

    private Logger logger = Logger.getLogger("PrintButtonListener");

    private AppController app;

    public PrintButtonListener(AppController app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {


            app.getServerTable().print();
            System.out.println(Thread.currentThread().getName());
        } catch (PrinterException e) {
            System.out.println("ERROR");
            logger.log(Level.WARNING, "打印机配置错误", e);
            JOptionPane.showMessageDialog(app.getFrame(), "没有设置打印机或者打印机设置错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}