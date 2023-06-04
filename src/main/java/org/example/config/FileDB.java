package org.example.config;

import java.io.File;

public class FileDB {
    public static File getFile() {
        return new File("./data.csv");
    }
}
