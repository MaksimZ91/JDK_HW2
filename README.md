# Урок 2. Программные интерфейсы

Выделил интерфейсы и попытался реализовать сокеты.

## ClientGUI interface 
```java
package org.example.Client;

public interface ClientGUI {
     void historyChat(String message);
}
```
## IClient interface
```java
package org.example.Client;
import java.io.*;

public interface IClient {
     boolean connect(String ip, int port, String name) throws IOException;
   String readMessage() throws IOException;
    void sendMessage(String message) throws IOException;
}
```
## ServerGUI interface 
```java
package org.example.Server;

public interface ServerGUI {
     void logger(String message);
}
```
## IServer interface
```java
package org.example.Server;

import java.io.IOException;

interface  IServer {
    void  serverStart() throws IOException;
    void  serverStop() throws IOException;
}
```
## Log interface
```java
package org.example.Loger;
import java.io.IOException;
public interface Log {
     void toLog(String message) throws IOException;
      String fromTheLog() throws IOException;
}
```
## Repository interface
```java
package org.example.Repository;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Repo {
    void write(String text, String name) throws IOException;
    String read(String name) throws FileNotFoundException;
}
```



