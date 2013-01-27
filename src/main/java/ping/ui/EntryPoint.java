package ping.ui;

import javax.swing.*;

public class EntryPoint {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        MainFame mainFame = new MainFame();
        mainFame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFame.pack();
        mainFame.setVisible(true);
    }
}