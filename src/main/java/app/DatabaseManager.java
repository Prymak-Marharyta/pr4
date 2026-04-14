package app;

import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;

public class DatabaseManager {
    private Connection connection;

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

public void insertBook(Book book) {
    String sql = "INSERT INTO books (type, title, author, year, price, genre, pages, size, cover, duration, narrator, condition, discount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        PreparedStatement ps = connection.prepareStatement(sql);

        // type
        ps.setString(1, book.getClass().getSimpleName());

        // базові поля
        ps.setString(2, book.getTitle());
        ps.setString(3, book.getAuthor());
        ps.setInt(4, book.getYear());
        ps.setDouble(5, book.getPrice());
        ps.setString(6, book.getGenre().toString());
        ps.setInt(7, book.getPages());

        // додаткові (по дефолту null)
        ps.setObject(8, null);
        ps.setObject(9, null);
        ps.setObject(10, null);
        ps.setObject(11, null);
        ps.setObject(12, null);
        ps.setObject(13, null);

        // перевірка підкласів
        if (book instanceof EBook) {
            ps.setDouble(8, ((EBook) book).getFileSize());
        }

        if (book instanceof PaperBook) {
            ps.setString(9, ((PaperBook) book).getCoverType());
        }

        if (book instanceof AudioBook) {
            ps.setInt(10, ((AudioBook) book).getDuration());
            ps.setString(11, ((AudioBook) book).getNarrator());
        }

        if (book instanceof UsedBook) {
            ps.setString(12, ((UsedBook) book).getCondition());
            ps.setDouble(13, ((UsedBook) book).getDiscount());
        }

        ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Помилка INSERT");
            e.printStackTrace();
        }
    }
}