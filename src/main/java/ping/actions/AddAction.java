package ping.actions;

import ping.actions.CreateServerAction;
import ping.ui.AppController;
import ping.ui.server.ServerEditPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddAction implements ActionListener {

    private AppController app;

    public AddAction(AppController app) {
        this.app = app;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ServerEditPanel panel = new ServerEditPanel("新增服务器");

        final JDialog dialog = new JDialog(app.getFrame());


        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.setLocation(
                (int)(dialog.getParent().getLocation().getX() + dialog.getParent().getWidth() / 4),
                (int)(dialog.getParent().getLocation().getY() + dialog.getParent().getHeight() / 4)
        );




        Container buttonContainer = new Container();
        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.LINE_AXIS));

        JButton okButton = new JButton("确定");
        okButton.addActionListener(new CreateServerAction(panel, app, dialog));

        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dialog.setVisible(false);
            }
        });

        buttonContainer.add(okButton);
        buttonContainer.add(cancelButton);


        dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.PAGE_AXIS));
        dialog.add(panel);
        dialog.add(buttonContainer);

        dialog.pack();
        dialog.setVisible(true);
    }
}