package ping.ui.table;

import ping.core.Server;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ServerLatencyCellRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {

        String text = null;

        int intVal = Integer.parseInt(value.toString());

        if (intVal == Server.NO_LATENCY) {
            text = "--";
        } else {
            text = String.valueOf(intVal);
        }

        return new JLabel(text);
    }
}
