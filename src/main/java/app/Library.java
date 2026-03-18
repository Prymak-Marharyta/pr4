package app;

import java.util.ArrayList;
import java.util.List;

// Клас бібліотеки (агрегує книги)
public class Library {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void showBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
    }
}