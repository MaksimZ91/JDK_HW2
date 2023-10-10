package org.example.Server;

import java.io.IOException;

interface  IServer {
    void  serverStart() throws IOException;
    void  serverStop() throws IOException;
}
