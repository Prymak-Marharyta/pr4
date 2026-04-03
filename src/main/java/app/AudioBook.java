package app;

public class AudioBook extends Book {

    private int duration; // хвилини
    private String narrator;

    public AudioBook(String title, String author, int year, double price,
                     Genre genre, int pages, int duration, String narrator) {
        super(title, author, year, price, genre, pages);
        setDuration(duration);
        setNarrator(narrator);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration <= 0)
            throw new IllegalArgumentException("Тривалість має бути > 0");
        this.duration = duration;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        if (narrator == null || narrator.isBlank())
            throw new IllegalArgumentException("Диктор не може бути порожнім");
        this.narrator = narrator;
    }

    @Override
    public String toString() {
        return "AudioBook{" +
                "title='" + getTitle() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", duration=" + duration +
                ", narrator='" + narrator + '\'' +
                '}';
    }
}