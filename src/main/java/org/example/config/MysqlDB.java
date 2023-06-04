package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlDB {
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url = "jdbc:mysql://localhost:3306/hospital";
                String username = "lukash";
                String password = "root";
                conn = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return conn;
    }
}