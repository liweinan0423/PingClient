package ping.ui.toolbar;

import javax.swing.*;


public class PToolBar extends JToolBar {

    private static final String ADD = "增加服务器";
    private static final String REMOVE = "删除";
    private static final String START_PING = "开始Ping";
    private static final String PRINT = "打印";

    private JButton addButton;
    private JButton removeButton;
    private JButton printButton;
    private JButton startPingBtn;

    public PToolBar() {
        super();
        
        addButton = new JButton(ADD);
        removeButton = new JButton(REMOVE);
        printButton = new JButton(PRINT);
        startPingBtn = new JButton(START_PING);

        this.add(addButton);
        this.add(removeButton);
        this.add(new Separator());
        this.add(startPingBtn);
        this.add(new Separator());
        this.add(printButton);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getStartPingBtn() {
        return startPingBtn;
    }

    public JButton getPrintButton() {
        return printButton;
    }
}
