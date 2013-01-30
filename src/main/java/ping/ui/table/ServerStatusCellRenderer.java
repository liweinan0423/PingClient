package ping.ui.table;

import ping.core.Server;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ServerStatusCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String text = "";

        int intVal = Integer.parseInt(value.toString());

        switch (intVal) {
            case Server.NORMAL:
                text = Server.NORMAL_TEXT;
                break;
            case Server.EXCEPTION:
                text = Server.EXCEPTION_TEXT;
                break;
            case Server.UNKNOWN:
                text = Server.UNKOWN_TEXT;
                break;
            default:
                text = Server.UNKOWN_TEXT;
        }


        return new JLabel(text);
    }

}
