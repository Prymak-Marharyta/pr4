package app;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int year;
    private double price;
    private String genre;  // новий атрибут
    private int pages;     // новий атрибут

    // Конструктор з перевірками через сеттери
    public Book(String title, String author, int year, double price, String genre, int pages) {
        setTitle(title); // перевірка, щоб назва не була порожньою
        setAuthor(author); // перевірка, щоб автор не був порожнім
        setYear(year); // перевірка на логічний рік
        setPrice(price); // перевірка, щоб ціна >= 0
        setGenre(genre); // перевірка, щоб жанр не був порожнім
        setPages(pages); // перевірка, щоб сторінок > 0
    }

    // додано перевірку для коректності назви книги
    public String getTitle() { return title; }
    public void setTitle(String title) {
    if (title == null || title.isBlank()) {
        throw new IllegalArgumentException("Назва не може бути порожньою");
    }
    this.title = title;
    }

    // додано перевірку, щоб автор не був порожнім
    public String getAuthor() { return author; }
        public void setAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Автор не може бути порожнім");
        }
        this.author = author;
    }

    // додано перевірку на логічний рік видання
    public int getYear() { return year; }
       public void setYear(int year) {
        if (year < 0 || year > 3000) {
            throw new IllegalArgumentException("Рік повинен бути в діапазоні [1; 2100]");
        }
        this.year = year;
    }

    // додано перевірку, щоб ціна не була від’ємною
    public double getPrice() { return price; }
    public void setPrice(double price) {
    if (price < 0) {
        throw new IllegalArgumentException("Ціна не може бути від'ємною");
    }
    this.price = price;
    }

    // геттер і сеттер для нового поля genre
    // додано перевірку, щоб жанр не був порожнім
    public String getGenre() { return genre;} 
    public void setGenre(String genre) {
    if (genre == null || genre.isBlank()) {
        throw new IllegalArgumentException("Жанр не може бути порожнім");
    }
    this.genre = genre;
    }

    // геттер і сеттер для нового поля pages
    // додано перевірку, щоб кількість сторінок була більше 0
    public int getPages() { return pages; }
    public void setPages(int pages) {
    if (pages <= 0) {
        throw new IllegalArgumentException("Кількість сторінок має бути більше 0");
    }
    this.pages = pages;
    }

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
