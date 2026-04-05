package app;

import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;

public class JsonManager {

    public static void save(ArrayList<Book> books, String file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(books, writer);
        } catch (Exception e) {
            System.out.println("Помилка JSON запису");
        }
    }

    public static ArrayList<Book> load(String file) {
        Gson gson = new Gson();
        ArrayList<Book> books = new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            Book[] arr = gson.fromJson(reader, Book[].class);
            if (arr != null) {
                for (Book b : arr) books.add(b);
            }
        } catch (Exception e) {
            System.out.println("Помилка JSON читання");
        }

        return books;
    }
}