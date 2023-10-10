package org.example.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Repo {
    void write(String text, String name) throws IOException;
    String read(String name) throws FileNotFoundException;

}
