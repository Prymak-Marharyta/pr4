package app;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int year;
    private double price;
    private Genre genre; // тепер enum
    private int pages;

    private static int count = 0;

    // Конструктор з перевірками через сеттери
    public Book(String title, String author, int year, double price, Genre genre, int pages) {
        setTitle(title); // перевірка, щоб назва не була порожньою
        setAuthor(author); // перевірка, щоб автор не був порожнім
        setYear(year); // перевірка на логічний рік
        setPrice(price); // перевірка, щоб ціна >= 0
        setGenre(genre); // перевірка, щоб жанр не був порожнім
        setPages(pages); // перевірка, щоб сторінок > 0
        count++; // збільшуємо лічильник
    }

    // конструктор копіювання
    public Book(Book other) {
    this(other.title, other.author, other.year, other.price, other.genre, other.pages);
    }

     // static геттер
    public static int getCount() {
        return count;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Назва не може бути порожньою");
        this.title = title;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) {
        if (author == null || author.isBlank())
            throw new IllegalArgumentException("Автор не може бути порожнім");
        this.author = author;
    }

    public int getYear() { return year; }
    public void setYear(int year) {
        if (year < 0 || year > 3000)
            throw new IllegalArgumentException("Некоректний рік");
        this.year = year;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Ціна не може бути від’ємною");
        this.price = price;
    }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) {
        if (genre == null)
            throw new IllegalArgumentException("Жанр не може бути null");
        this.genre = genre;
    }
    
    public int getPages() { return pages; }
    public void setPages(int pages) {
        if (pages <= 0)
            throw new IllegalArgumentException("Сторінки > 0");
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", genre=" + genre +
                ", pages=" + pages +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return year == book.year &&
                Double.compare(book.price, price) == 0 &&
                pages == book.pages &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, price, genre, pages);
    }
}