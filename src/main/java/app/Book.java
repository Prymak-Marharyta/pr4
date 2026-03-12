package app;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int year;
    private double price;
    private String genre;  // новий атрибут
    private int pages;     // новий атрибут

    public Book(String title, String author, int year, double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.genre = genre; // нова характеристика книги
        this.pages = pages; // нова характеристика книги
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // геттер і сеттер для нового поля genre
    public String getGenre() { return genre;} 
    public void setGenre(String genre) { this.genre = genre;}

    // геттер і сеттер для нового поля pages
    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages;}

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", genre='" + genre + '\'' + // нове поле genre
                ", pages=" + pages + // нове поле pages
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return year == book.year
                && Double.compare(book.price, price) == 0
                && Objects.equals(title, book.title)
                && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, price);
    }
}