package org.example.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClintUI extends JFrame  implements  ClientGUI{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 480;
    private JButton btnLogin, btnSend, mock;
    private JTextArea chat;
    private JPanel topPanel, sendPanel, socketPanel, loginPanel;
    private JTextField ip, port, name, message;
    private JPasswordField password;
    private Client client;

    public ClintUI(Client client) {
        this.client = client;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Client chat");
        build();
        setResizable(false);
        setVisible(true);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (client.connect(ip.getText(), Integer.parseInt(port.getText()), name.getText()))
                        topPanel.setVisible(false);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.sendMessage(name.getText() + " " + message.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Override
    public void historyChat(String message) {
        chat.setText(message);
    }

    private void build() {
        add(top(), BorderLayout.NORTH);
        add(mid());
        add(bottom(), BorderLayout.SOUTH);
    }

    private Component mid() {
        chat = new JTextArea();
        return chat;
    }

    private Component top() {
        btnLogin = new JButton("Login");
        ip = new JTextField("127.0.0.1", 16);
        port = new JTextField("58421", 5);
        mock = new JButton("Mock");
        mock.setVisible(false);
        name = new JTextField("Ivan Ivanovich");
        password = new JPasswordField("*******");
        socketPanel = new JPanel(new GridLayout(1, 3));
        socketPanel.add(ip);
        socketPanel.add(port);
        socketPanel.add(mock);
        loginPanel = new JPanel(new GridLayout(1, 3));
        loginPanel.add(name);
        loginPanel.add(password);
        loginPanel.add(btnLogin);
        topPanel = new JPanel(new BorderLayout());
        topPanel.add(socketPanel, BorderLayout.NORTH);
        topPanel.add(loginPanel, BorderLayout.SOUTH);
        return topPanel;
    }

    private Component bottom() {
        btnSend = new JButton("Send");
        message = new JTextField();
        sendPanel = new JPanel(new GridLayout(1, 2));
        sendPanel.add(message);
        sendPanel.add(btnSend);
        return sendPanel;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

}
