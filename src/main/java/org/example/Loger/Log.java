package org.example.Loger;

import java.io.IOException;

public interface Log {

     void toLog(String message) throws IOException;

    String fromTheLog() throws IOException;
}
