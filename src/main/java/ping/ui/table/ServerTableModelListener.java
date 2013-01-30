package ping.ui.table;

import ping.core.Server;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.Vector;

import static ping.ui.table.ServerTableModel.extractServer;

public class ServerTableModelListener implements TableModelListener {


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
        model.getRepository().updateServer(server);
    }

    private void onInsert(TableModelEvent tableModelEvent, ServerTableModel model) {
    }
}
