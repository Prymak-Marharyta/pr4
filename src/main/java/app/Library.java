package app;

import java.util.ArrayList;

public class Library {

    private ArrayList<LibraryItem> items;

    public Library() {
        items = new ArrayList<>();
    }

    // додавання книги
    public void addNewBook(Book bk, int quantity) {

        for (LibraryItem item : items) {
            Book b = item.getBook();

            if (b.getTitle().equalsIgnoreCase(bk.getTitle()) &&
                b.getAuthor().equalsIgnoreCase(bk.getAuthor())) {

                item.addQuantity(quantity);
                return;
            }
        }

        items.add(new LibraryItem(bk, quantity));
    }

    // повертає список книг (для збереження у файл)
    public ArrayList<Book> toBookList() {
        ArrayList<Book> result = new ArrayList<>();

        for (LibraryItem item : items) {
            for (int i = 0; i < item.getQuantity(); i++) {
                result.add(item.getBook());
            }
        }

        return result;
    }

    // пошук за назвою
    public ArrayList<Book> searchByTitle(String title) {
        ArrayList<Book> result = new ArrayList<>();

        for (LibraryItem item : items) {
            if (item.getBook().getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(item.getBook());
            }
        }

        return result;
    }

    // пошук за автором
    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();

        for (LibraryItem item : items) {
            if (item.getBook().getAuthor().equalsIgnoreCase(author)) {
                result.add(item.getBook());
            }
        }

        return result;
    }

    // пошук за роком
    public ArrayList<Book> searchByYear(int year) {
        ArrayList<Book> result = new ArrayList<>();

        for (LibraryItem item : items) {
            if (item.getBook().getYear() == year) {
                result.add(item.getBook());
            }
        }

        return result;
    }

    // вивід
    public void printAll() {
        if (items.isEmpty()) {
            System.out.println("Бібліотека порожня");
        } else {
            for (LibraryItem item : items) {
                System.out.println(item);
            }
        }
    }
}