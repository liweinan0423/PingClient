package ping.ui.server;


import javax.swing.*;
import java.awt.*;

public class ServerEditPanel extends JPanel {

    private JLabel serverNameLabel;
    private JTextField serverNameField;

    private JLabel serverAddressLabel;
    private JTextField serverAddressField;

    public ServerEditPanel(String title) {


        serverNameLabel = new JLabel("服务器名称");
        serverNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        serverNameField = new JTextField(10);

        serverAddressLabel = new JLabel("服务器地址");
        serverAddressLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        serverAddressField = new JTextField(10);



        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(title),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));



        Container serverNameContainer = new Container();
        serverNameContainer.setLayout(new BoxLayout(serverNameContainer, BoxLayout.LINE_AXIS));
        serverNameContainer.add(serverNameLabel);
        serverNameContainer.add(serverNameField);

        Container serverAddressContainer = new Container();
        serverAddressContainer.setLayout(new BoxLayout(serverAddressContainer, BoxLayout.LINE_AXIS));
        serverAddressContainer.add(serverAddressLabel);
        serverAddressContainer.add(serverAddressField);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(serverNameContainer);
        this.add(serverAddressContainer);

    }

    public JTextField getServerNameField() {
        return serverNameField;
    }

    public JTextField getServerAddressField() {
        return serverAddressField;
    }
}
