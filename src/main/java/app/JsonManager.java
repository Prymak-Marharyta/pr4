package app;

import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;

public class JsonManager {

    public static void save(ArrayList<Book> books, String file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray array = new JsonArray();

        for (Book b : books) {
            JsonObject obj = (JsonObject) gson.toJsonTree(b);

            if (b instanceof EBook) obj.addProperty("type", "EBook");
            else if (b instanceof PaperBook) obj.addProperty("type", "PaperBook");
            else if (b instanceof AudioBook) obj.addProperty("type", "AudioBook");
            else if (b instanceof UsedBook) obj.addProperty("type", "UsedBook");
            else obj.addProperty("type", "Book");

            array.add(obj);
        }

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(array, writer);
        } catch (Exception e) {
            System.out.println("Помилка JSON запису: " + e.getMessage());
        }
    }

    public static ArrayList<Book> load(String file) {
        ArrayList<Book> books = new ArrayList<>();
        Gson gson = new Gson(); 

        try (FileReader reader = new FileReader(file)) {
            JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement elem : array) {
                JsonObject obj = elem.getAsJsonObject();
                String type = obj.get("type").getAsString();

                Book book;
                switch (type) {
                    case "EBook":
                        book = gson.fromJson(elem, EBook.class);
                        break;
                    case "PaperBook":
                        book = gson.fromJson(elem, PaperBook.class);
                        break;
                    case "AudioBook":
                        book = gson.fromJson(elem, AudioBook.class);
                        break;
                    case "UsedBook":
                        book = gson.fromJson(elem, UsedBook.class);
                        break;
                    default:
                        book = gson.fromJson(elem, Book.class);
                        break;
                }

                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("Помилка JSON читання: " + e.getMessage());
        }

        return books;
    }
}