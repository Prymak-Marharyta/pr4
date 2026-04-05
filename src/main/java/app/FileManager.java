package app;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static ArrayList<Book> loadFromFile(String fileName) {
        ArrayList<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(";");

                    String type = parts[0];

                    String title = parts[1];
                    String author = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);
                    Genre genre = Genre.valueOf(parts[5]);
                    int pages = Integer.parseInt(parts[6]);

                    switch (type) {
                        case "Book":
                            books.add(new Book(title, author, year, price, genre, pages));
                            break;

                        case "EBook":
                            double size = Double.parseDouble(parts[7]);
                            books.add(new EBook(title, author, year, price, genre, pages, size));
                            break;

                        case "PaperBook":
                            books.add(new PaperBook(title, author, year, price, genre, pages, parts[7]));
                            break;

                        case "AudioBook":
                            int duration = Integer.parseInt(parts[7]);
                            String narrator = parts[8];
                            books.add(new AudioBook(title, author, year, price, genre, pages, duration, narrator));
                            break;

                        case "UsedBook":
                            String condition = parts[7];
                            double discount = Double.parseDouble(parts[8]);
                            books.add(new UsedBook(title, author, year, price, genre, pages, condition, discount));
                            break;
                    }

                } catch (Exception e) {
                    System.out.println("Помилка в рядку: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Файл не знайдено, тому буде створено новий.");
        }

        return books;
    }
}