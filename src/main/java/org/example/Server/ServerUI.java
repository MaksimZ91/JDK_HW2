package org.example.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerUI extends JFrame implements  ServerGUI{
    private static final int WIDTH = 450;
    private static final int HEIGHT = 480;
    JButton btnStart, btnStop;
    JTextArea log;
    JPanel bottomPanel;
    Server server;

    public ServerUI(Server server) {
        this.server = server;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Chat server");
        bottomPanel = new JPanel(new GridLayout(1, 2));
        setResizable(false);
        log = new JTextArea();
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        bottomPanel.add(btnStart);
        bottomPanel.add(btnStop);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    server.serverStart();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    server.serverStop();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);

                }
            }
        });
        add(log);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void logger(String message) {
        log.setText(message);
    }

}
