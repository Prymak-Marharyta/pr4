package app;

public class LibraryItem {
    private Book book;
    private int quantity;

    public LibraryItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int q) {
        this.quantity += q;
    }

    @Override
    public String toString() {
        return book.toString() + " | Кількість: " + quantity;
    }
}