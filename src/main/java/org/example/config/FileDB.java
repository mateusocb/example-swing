package org.example.config;

import java.io.File;

public class FileDB {
    public static File getFile() {
        File file = new File("./data.csv");

        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }
}
