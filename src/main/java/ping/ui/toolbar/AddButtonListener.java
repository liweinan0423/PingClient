package ping.ui.toolbar;

import ping.actions.CreateServerAction;
import ping.ui.AppController;
import ping.ui.server.ServerEditPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddButtonListener implements ActionListener {

    private AppController app;

    public AddButtonListener(AppController app) {
        this.app = app;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ServerEditPanel panel = new ServerEditPanel("新增服务器");

        JDialog dialog = new JDialog(app.getFrame());

        dialog.setContentPane(panel);
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.setBounds(dialog.getParent().getWidth() / 4, dialog.getParent().getHeight() / 2, 0, 0);

        JButton okButton = new JButton("确定");

        okButton.setAction(new CreateServerAction(panel, app, dialog));

        dialog.add(okButton);
        dialog.pack();
        dialog.setVisible(true);
    }
}