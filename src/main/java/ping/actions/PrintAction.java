package ping.actions;

import ping.ui.AppController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintAction implements ActionListener {

    private Logger logger = Logger.getLogger("PrintAction");

    private AppController app;

    public PrintAction(AppController app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {

            app.getServerTable().print();

        } catch (Exception e) {
            if (e.getCause() instanceof PrinterException) {
                logger.log(Level.SEVERE, "打印机配置错误", e);
                JOptionPane.showMessageDialog(app.getFrame(), "没有安装打印机或者打印机设置错误", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                throw new RuntimeException(e);
            }
        }
    }
}