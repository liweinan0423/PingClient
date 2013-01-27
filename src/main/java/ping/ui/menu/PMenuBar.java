package ping.ui.menu;

import javax.swing.*;


public class PMenuBar extends JMenuBar {

    private static final String FILE = "文件";
    private static final String OPTIONS = "选项";
    private static final String HELP = "帮助";

    private JMenu fileMenu;
    private JMenu optionsMenu;
    private JMenu helpMenu;

    public PMenuBar() {
        super();

        fileMenu = new JMenu(FILE);
        optionsMenu = new JMenu(OPTIONS);
        helpMenu = new JMenu(HELP);

        this.add(fileMenu);
        this.add(optionsMenu);
        this.add(helpMenu);

    }

}