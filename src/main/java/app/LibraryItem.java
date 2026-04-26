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

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return book + ", quantity=" + quantity;
    }
}