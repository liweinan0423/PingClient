package ping.actions;

import ping.core.DuplicateServerDefinitionException;
import ping.core.Server;
import ping.ui.AppController;
import ping.ui.server.ServerEditPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreateServerAction extends AbstractAction {

    private ServerEditPanel panel;
    private AppController app;
    private JDialog dialog;

    public CreateServerAction(ServerEditPanel panel, AppController app, JDialog dialog) {
        this.panel = panel;
        this.app = app;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String serverName = panel.getServerNameField().getText();
        String serverAddress = panel.getServerAddressField().getText();

        if (serverName == null || "".equals(serverName)) {
            JOptionPane.showMessageDialog(panel, "服务器名称不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (serverAddress == null || "".equals(serverAddress)) {
            JOptionPane.showMessageDialog(panel, "服务器地址不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }


        Server server = new Server();
        server.setName(serverName);
        server.setAddress(serverAddress);
        try {
            app.getServerRepository().saveServer(server);
            dialog.setVisible(false);

        } catch (DuplicateServerDefinitionException e) {
            JOptionPane.showMessageDialog(panel, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }


    }
}