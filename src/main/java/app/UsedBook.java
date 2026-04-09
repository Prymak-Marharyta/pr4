package app;

public class UsedBook extends Book {

    private String condition;
    private double discount;

    public UsedBook(String title, String author, int year, double price,
                    Genre genre, int pages, String condition, double discount) {
        super(title, author, year, price, genre, pages);
        setCondition(condition);
        setDiscount(discount);
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        if (condition == null || condition.isBlank())
            throw new IllegalArgumentException("Стан не може бути порожнім");
        this.condition = condition;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        if (discount < 0 || discount > 100)
            throw new IllegalArgumentException("Знижка 0-100%");
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "UsedBook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", year=" + getYear() +
                ", price=" + getPrice() +
                ", genre=" + getGenre() +
                ", pages=" + getPages() +
                ", condition='" + condition + '\'' +
                ", discount=" + discount + "%" +
                '}';
    }
}