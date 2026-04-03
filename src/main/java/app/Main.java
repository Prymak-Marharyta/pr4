package app;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();

        System.out.println("=== Система обліку книг ===");

        while (true) {
            System.out.println("1. Додати Book");
            System.out.println("2. Додати EBook");
            System.out.println("3. Додати PaperBook");
            System.out.println("4. Додати AudioBook");
            System.out.println("5. Додати UsedBook");
            System.out.println("6. Показати всі");
            System.out.println("7. Вихід");

            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1": {
                        Book book = createBaseBook(sc);
                        books.add(book);
                        System.out.println("Додано Book");
                        break;
                    }

                    case "2": {
                        Book base = createBaseBook(sc);

                        double fileSize = readDouble(sc, "Розмір файлу (MB): ", 0, 10000);

                        EBook ebook = new EBook(
                                base.getTitle(),
                                base.getAuthor(),
                                base.getYear(),
                                base.getPrice(),
                                base.getGenre(),
                                base.getPages(),
                                fileSize
                        );

                        books.add(ebook);
                        System.out.println("Додано EBook");
                        break;
                    }

                    case "3": {
                        Book base = createBaseBook(sc);

                        System.out.print("Тип обкладинки: ");
                        String cover = sc.nextLine();

                        PaperBook pb = new PaperBook(
                                base.getTitle(),
                                base.getAuthor(),
                                base.getYear(),
                                base.getPrice(),
                                base.getGenre(),
                                base.getPages(),
                                cover
                        );

                        books.add(pb);
                        System.out.println("Додано PaperBook");
                        break;
                    }

                    case "4": {
                        Book base = createBaseBook(sc);

                        int duration = readInt(sc, "Тривалість (хв): ", 1, 10000);

                        System.out.print("Диктор: ");
                        String narrator = sc.nextLine();

                        AudioBook ab = new AudioBook(
                                base.getTitle(),
                                base.getAuthor(),
                                base.getYear(),
                                base.getPrice(),
                                base.getGenre(),
                                base.getPages(),
                                duration,
                                narrator
                        );

                        books.add(ab);
                        System.out.println("Додано AudioBook");
                        break;
                    }

                    case "5": {
                        Book base = createBaseBook(sc);

                        System.out.print("Стан книги: ");
                        String condition = sc.nextLine();

                        double discount = readDouble(sc, "Знижка (%): ", 0, 100);

                        UsedBook ub = new UsedBook(
                                base.getTitle(),
                                base.getAuthor(),
                                base.getYear(),
                                base.getPrice(),
                                base.getGenre(),
                                base.getPages(),
                                condition,
                                discount
                        );

                        books.add(ub);
                        System.out.println("Додано UsedBook");
                        break;
                    }
                    
                    case "6":
                        System.out.println("\n--- Список ---");
                        for (Book b : books) {
                            System.out.println(b); // ПОЛІМОРФІЗМ
                        }
                        break;

                    case "7":
                        System.out.println("До побачення!");
                        return;

                    default:
                        System.out.println("Невірний вибір");
                }

            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }
                
    private static Book createBaseBook(Scanner sc) {
        System.out.print("Назва: ");
        String title = sc.nextLine();

        System.out.print("Автор: ");
        String author = sc.nextLine();

        int year = readInt(sc, "Рік: ", 0, 3000);
        double price = readDouble(sc, "Ціна: ", 0, 100000);

        System.out.println("Жанр (FICTION, SCIENCE, HISTORY, FANTASY, DETECTIVE): ");
        Genre genre = Genre.valueOf(sc.nextLine().trim().toUpperCase());

        int pages = readInt(sc, "Сторінки: ", 1, 10000);

        return new Book(title, author, year, price, genre, pages);
    }

    // метод для безпечного читання int з перевіркою діапазону
    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine());
                if (value < min || value > max) throw new Exception();
                return value;
            } catch (Exception e) {
                System.out.println("Помилка вводу");
            }
        }
    }


    // метод для безпечного читання double з перевіркою діапазону
    private static double readDouble(Scanner sc, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine().replace(',', '.'));
                if (value < min || value > max) throw new Exception();
                return value;
            } catch (Exception e) {
                System.out.println("Помилка вводу");
            }
        }
    }
}