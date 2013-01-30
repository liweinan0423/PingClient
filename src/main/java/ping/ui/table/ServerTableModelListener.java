package ping.ui.table;

import ping.core.DuplicateServerDefinitionException;
import ping.core.Server;
import ping.ui.AppController;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.Vector;

import static ping.ui.table.ServerTableModel.extractServer;

public class ServerTableModelListener implements TableModelListener {

    private AppController app;

    public ServerTableModelListener(AppController app) {
        this.app = app;
    }

    @Override
    public void tableChanged(TableModelEvent tableModelEvent) {

        ServerTableModel model = (ServerTableModel) tableModelEvent.getSource();

        switch (tableModelEvent.getType()) {
            case TableModelEvent.INSERT:
                onInsert(tableModelEvent, model);
                break;
            case TableModelEvent.UPDATE:
                onUpdate(tableModelEvent, model);
                break;
            case TableModelEvent.DELETE:
                onDelete(tableModelEvent, model);
                break;
        }

    }

    private void onDelete(TableModelEvent tableModelEvent, ServerTableModel model) {

    }

    private void onUpdate(TableModelEvent tableModelEvent, ServerTableModel model) {
        int firstRow = tableModelEvent.getFirstRow();
        Vector serverDataVector = (Vector) model.getDataVector().get(firstRow);
        Server server = extractServer(serverDataVector);
        try {
            model.getRepository().updateServer(server);
        } catch (DuplicateServerDefinitionException e) {
            JOptionPane.showMessageDialog(app.getFrame(), e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onInsert(TableModelEvent tableModelEvent, ServerTableModel model) {
    }
}
