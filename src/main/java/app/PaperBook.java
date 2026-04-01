package app;

public class PaperBook extends Book {

    private String coverType; // тип обкладинки

    public PaperBook(String title, String author, int year, double price,
                     Genre genre, int pages, String coverType) {
        super(title, author, year, price, genre, pages);
        setCoverType(coverType);
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        if (coverType == null || coverType.isBlank())
            throw new IllegalArgumentException("Тип обкладинки не може бути порожнім");
        this.coverType = coverType;
    }

    @Override
    public String toString() {
        return "PaperBook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year=" + getYear() +
                ", price=" + getPrice() +
                ", genre=" + getGenre() +
                ", pages=" + getPages() +
                ", coverType='" + coverType + '\'' +
                '}';
    }
}