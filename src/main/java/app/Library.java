package app;

import app.exceptions.ObjectNotFoundException;

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

    public void update(Book existingObject, Book newObject) {
        for (LibraryItem item : items) {
            if (item.getBook().equals(existingObject)) {
                item.setBook(newObject);
                return;
            }
        }

        throw new ObjectNotFoundException("Книгу для модифікації не знайдено");
    }

    public void delete(Book existingObject) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getBook().equals(existingObject)) {
                items.remove(i);
                return;
            }
        }

        throw new ObjectNotFoundException("Книгу для видалення не знайдено");
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getItemsCount() {
        return items.size();
    }

    public Book getBookByNumber(int number) {
        if (number < 1 || number > items.size()) {
            throw new ObjectNotFoundException("Книгу з таким номером не знайдено");
        }

        return items.get(number - 1).getBook();
    }
    
    public void printAllWithNumbers() {
        if (items.isEmpty()) {
            System.out.println("Бiблiотека порожня");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
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
    
    public void printSortedByTitle() {
        if (items.isEmpty()) {
            System.out.println("Бiблiотека порожня");
            return;
        }

    ArrayList<Book> books = toBookList();

    books.sort((o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()));

        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void printSortedByYear() {
        if (items.isEmpty()) {
            System.out.println("Бiблiотека порожня");
            return;
        }

    ArrayList<Book> books = toBookList();

    books.sort((o1, o2) -> Integer.compare(o1.getYear(), o2.getYear()));

        for (Book b : books) {
            System.out.println(b);
        }
    }
    
    public void printSortedByPrice() {

    if (items.isEmpty()) {
        System.out.println("Бiблiотека порожня");
        return;
    }

    ArrayList<Book> books = toBookList();

    books.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));

        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void printSorted() {

        if (items.isEmpty()) {
            System.out.println("Бiблiотека порожня");
            return;
        }

        ArrayList<Book> books = toBookList();

        books.sort(null);

        for (Book b : books) {
            System.out.println(b);
        }
    }

    // вивід
    public void printAll() {
        if (items.isEmpty()) {
            System.out.println("Бiблiотека порожня");
        } else {
            for (LibraryItem item : items) {
                System.out.println(item);
            }
        }
    }
}