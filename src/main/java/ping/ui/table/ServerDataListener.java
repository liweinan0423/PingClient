package ping.ui.table;

import ping.core.Server;
import ping.core.event.DataChangeEventListener;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ServerDataListener implements DataChangeEventListener {

    private DefaultTableModel tableModel;

    public ServerDataListener(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }


    @Override
    public void onServerCreate(Server server) {
        tableModel.addRow(ServerTable.getServerDataVector(server));
    }

    @Override
    public void onServerRemove(Server server) {

        List<Integer> rowsToBeRemoved = new ArrayList<Integer>();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(server.getId())) {
                rowsToBeRemoved.add(i);
            }
        }

        for (Integer rowIndex : rowsToBeRemoved) {
            tableModel.removeRow(rowIndex);
        }
    }

    @Override
    public void onServerUpdate(Server server) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
