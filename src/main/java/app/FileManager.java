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

                        default:
                            System.out.println("Невідомий тип: " + type);
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

    // метод для збереження даних у файл
    public static void saveToFile(ArrayList<Book> books, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            for (Book b : books) {
                String line;

                if (b instanceof EBook) {
                    EBook e = (EBook) b;
                    line = "EBook;" + e.getTitle() + ";" + e.getAuthor() + ";" +
                            e.getYear() + ";" + e.getPrice() + ";" + e.getGenre() +
                            ";" + e.getPages() + ";" + e.getFileSize();

                } else if (b instanceof PaperBook) {
                    PaperBook p = (PaperBook) b;
                    line = "PaperBook;" + p.getTitle() + ";" + p.getAuthor() + ";" +
                            p.getYear() + ";" + p.getPrice() + ";" + p.getGenre() +
                            ";" + p.getPages() + ";" + p.getCoverType();

                } else if (b instanceof AudioBook) {
                    AudioBook a = (AudioBook) b;
                    line = "AudioBook;" + a.getTitle() + ";" + a.getAuthor() + ";" +
                            a.getYear() + ";" + a.getPrice() + ";" + a.getGenre() +
                            ";" + a.getPages() + ";" + a.getDuration() + ";" + a.getNarrator();

                } else if (b instanceof UsedBook) {
                    UsedBook u = (UsedBook) b;
                    line = "UsedBook;" + u.getTitle() + ";" + u.getAuthor() + ";" +
                            u.getYear() + ";" + u.getPrice() + ";" + u.getGenre() +
                            ";" + u.getPages() + ";" + u.getCondition() + ";" + u.getDiscount();

                } else {
                    // на случай ошибки — просто пропускаем
                    continue;
                }

                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
        }
    }
}