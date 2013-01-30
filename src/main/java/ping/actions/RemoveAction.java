package ping.actions;

import ping.ui.AppController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveAction implements ActionListener {

    private final AppController app;

    public RemoveAction(AppController app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (app.getServerTable().getSelectedColumnCount() == 0) {
            JOptionPane.showMessageDialog(app.getFrame(), "请选择要删除的服务器", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }



        if (JOptionPane.YES_OPTION ==  JOptionPane.showConfirmDialog(app.getFrame(), "是否确定删除所选的服务器?")) {

            int[] selectedRows = app.getServerTable().getSelectedRows();


            String[] serverIds = new String[selectedRows.length];

            for (int i = 0; i < selectedRows.length; i++) {
                String serverId = app.getServerTable().getModel().getValueAt(selectedRows[i], 0).toString();
                serverIds[i] = serverId;
            }

            for (String id : serverIds) {
                app.getServerRepository().removeServerById(id);
            }
        }
    }
}
