package app;

import java.util.Objects;
import java.util.UUID;

public abstract class Book implements Comparable<Book>, Identifiable {
    private UUID uuid;
    private String title;
    private String author;
    private int year;
    private double price;
    private Genre genre;
    private int pages;

    // Конструктор з перевірками через сеттери
    public Book(String title, String author, int year, double price, Genre genre, int pages) {
        this.uuid = UUID.randomUUID();
        
        setTitle(title); // перевірка, щоб назва не була порожньою
        setAuthor(author); // перевірка, щоб автор не був порожнім
        setYear(year); // перевірка на логічний рік
        setPrice(price); // перевірка, щоб ціна >= 0
        setGenre(genre); // перевірка, щоб жанр не був порожнім
        setPages(pages); // перевірка, щоб сторінок > 0
    }

    // конструктор копіювання
    public Book(Book other) {
        this.uuid = other.uuid;
        this.title = other.title;
        this.author = other.author;
        this.year = other.year;
        this.price = other.price;
        this.genre = other.genre;
        this.pages = other.pages;
    }

    @Override
    public UUID getUuid() {
        return uuid;
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
            throw new IllegalArgumentException("Автор не може бути порожнiм");
        this.author = author;
    }

    public int getYear() { return year; }
    public void setYear(int year) {
        if (year < 0 || year > 3000)
            throw new IllegalArgumentException("Некоректний рiк");
        this.year = year;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Цiна не може бути вiд’ємною");
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
            throw new IllegalArgumentException("Сторiнки > 0");
        this.pages = pages;
    }

    public String toShortString() {
        return getClass().getSimpleName() + ": " + title + " | UUID: " + uuid;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
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
        return Objects.equals(uuid, book.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public int compareTo(Book other) {
        return this.getTitle().compareToIgnoreCase(other.getTitle());
    }
}