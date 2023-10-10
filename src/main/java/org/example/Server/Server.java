package org.example.Server;


import org.example.Loger.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server extends Thread implements IServer{
    private final ServerUI serverUI = new ServerUI(this);
    private static boolean isStop = false;
    private static final Logger logger = new Logger();
    private static final int PORT = 58421;
    private static  LinkedList<SocketList> socketList = new LinkedList<SocketList>();
    private StringBuffer sb = new StringBuffer();

    @Override
    public void run() {
        ServerSocket server = null;
        System.out.println(isInterrupted());
        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            while (!isInterrupted()) {
                Socket socket = null;
                try {
                    socket = server.accept();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    socketList.add(new SocketList(socket));
                    String message = sb.append(logger.fromTheLog()).toString();
                    for (SocketList sc : socketList) {
                        sc.send(sb.toString());
                        serverUI.logger(message);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void serverStart() throws IOException {
        start();
        isStop = false;
        if (!isStop) {
            String message = "Server start!";
            logger.toLog(message);
            sb.append(message).append(System.lineSeparator());
            serverUI.logger(sb.toString());
        }
    }
    @Override
    public void serverStop() throws IOException {
        isStop = true;
        for (SocketList sc : socketList) {
            if (isStop) {
                String message = "Server stop!";
                sc.send(message);
                logger.toLog(message);
                sb.append(message).append(System.lineSeparator());
                serverUI.logger(sb.toString());
            }
            sc.interrupt();
            sc.out.close();
            sc.in.close();
        }
        interrupt();
    }

    private class SocketList extends Thread implements  IServerSend {
        private Socket socket;
        private final BufferedWriter out;
        private final BufferedReader in;
        private SocketList(Socket socket) throws IOException {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()), 1024);
            start();
        }
        @Override
        public void run() {
            String message;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    message = in.readLine();
                    for (SocketList sr : Server.socketList) {
                        sr.send(message);
                        logger.toLog(message);
                        sb.append(message).append(System.lineSeparator());
                        serverUI.logger(sb.toString());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void send(String message) {
            try {
                out.write(message + "\n");
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

