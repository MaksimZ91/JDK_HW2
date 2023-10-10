package org.example.Client;

import java.io.*;
import java.net.Socket;

public interface IClient {
     boolean connect(String ip, int port, String name) throws IOException;
   String readMessage() throws IOException;
    void sendMessage(String message) throws IOException;
}
