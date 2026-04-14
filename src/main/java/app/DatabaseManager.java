package app;

import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;

public DatabaseManager(String configPath) {
    try {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(configPath);
        props.load(fis);

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        connection = DriverManager.getConnection(url, user, password);

    } catch (Exception e) {
        System.out.println("Помилка підключення до БД");
        e.printStackTrace();
    }
}