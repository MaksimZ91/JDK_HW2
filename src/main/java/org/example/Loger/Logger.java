package org.example.Loger;

import org.example.Repository.Repository;

import java.io.IOException;

public class Logger implements Log {
    private final Repository repo = new Repository();

    @Override
    public void toLog(String message) throws IOException {
        repo.write(message, "log");
    }

    @Override
    public String fromTheLog() throws IOException {
        return repo.read("log");
    }
}
