package org.example.Client;

import java.io.*;
import java.net.Socket;

public class Client extends Thread  implements  IClient{
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private String name = null;
    private final ClintUI clientUI = new ClintUI(this);

    @Override
    public void run() {
        StringBuffer sb = new StringBuffer();
        while (!isInterrupted()) {
            try {
                String data = readMessage();
                if (data == null) {
                    in.close();
                    out.close();
                    clientSocket.close();
                    clientUI.getTopPanel().setVisible(true);
                    interrupt();
                } else {
                    sb.append(data).append(System.lineSeparator());
                    clientUI.historyChat(sb.toString());
                }
            } catch (Exception e) {
                interrupt();
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public boolean connect(String ip, int port, String name) throws IOException {
        try {
            clientSocket = new Socket(ip, port);
            this.name = name;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            start();
            if (name != null) {
                String message = name + " connect!";
                sendMessage(message);
            }
            return true;
        } catch (IOException e) {
            in.close();
            out.close();
            clientSocket.close();
            interrupt();
            return false;
        }
    }
    @Override
    public  String readMessage() throws IOException {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public void sendMessage(String message) throws IOException {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
