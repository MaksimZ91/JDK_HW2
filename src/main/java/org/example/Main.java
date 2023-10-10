package org.example;

import org.example.Client.Client;
import org.example.Server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new Client();
            new Server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}