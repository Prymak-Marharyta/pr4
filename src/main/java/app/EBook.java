package app;

public class EBook extends Book {

    private double fileSize;

    public EBook(String title, String author, int year, double price,
                 Genre genre, int pages, double fileSize) {
        super(title, author, year, price, genre, pages);
        setFileSize(fileSize);
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        if (fileSize <= 0)
            throw new IllegalArgumentException("Розмір файлу має бути > 0");
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "EBook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year=" + getYear() +
                ", price=" + getPrice() +
                ", genre=" + getGenre() +
                ", pages=" + getPages() +
                ", fileSize=" + fileSize +
                " MB}";
    }
}