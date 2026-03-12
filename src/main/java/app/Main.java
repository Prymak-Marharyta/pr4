package app;
import java.util.List; // інтерфейс List для створення списків (ArrayList)
import java.util.ArrayList; // замінено масив на ArrayList
import java.util.Scanner; // додано для зберігання книг у списку

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Book> books = new ArrayList<>(); // змінено: замість масиву тепер список

        books.add(new Book("1984", "George Orwell", 1949, 250.0, "Dystopia", 328));
        books.add(new Book("Fahrenheit 451", "Ray Bradbury", 1953, 220.0, "Dystopia", 256));

        while (true) { // додано консольне меню
            System.out.println("\nМеню:");
            System.out.println("1. Додати нову книгу"); // додано пункт меню
            System.out.println("2. Показати всі книги"); // додано пункт меню
            System.out.println("3. Вихід"); // додано: пункт меню
            System.out.print("Вибір: ");

            String choice = sc.nextLine().trim();

            switch (choice) { // додана обробка вибору
                case "1": 
                 try { // додано try-catch для обробки некоректного введення
                 System.out.print("Назва: ");
                        String title = sc.nextLine().trim();

                        System.out.print("Автор: ");
                        String author = sc.nextLine().trim();

                        int year = readInt(sc, "Рік видання: ", 0, 3000); // перевірка введення int
                        double price = readDouble(sc, "Ціна: ", 0.0, 1_000_000.0); // перевірка введення double

                        System.out.print("Жанр: "); // новий атрибут genre
                        String genre = sc.nextLine().trim();
                        int pages = readInt(sc, "Кількість сторінок: ", 1, 10000); // новий атрибут pages

                        books.add(new Book(title, author, year, price, genre, pages)); // створення книги з новим конструктором
                        System.out.println("Книга додана!");
                    } catch (IllegalArgumentException e) { // обробка помилок від валідації
                        System.out.println("Помилка: " + e.getMessage());
                    }
                    break;
                case "2": // показати всі книги
                    System.out.println("\n--- Усі книги ---");
                    for (Book b : books) { // перебір ArrayList
                        System.out.println(b);
                    }
                    break;
                case "3": // вихiд
                    System.out.println("До побачення!");
                    sc.close();
                    return;
                default: // обробка некоректного вибору
                    System.out.println("Некоректний вибір. Спробуй ще раз.");
            }
        }
    }
    // метод для безпечного читання int з перевіркою діапазону
    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(s);
                if (value < min || value > max) throw new IllegalArgumentException();
                return value;
            } catch (Exception e) {
                System.out.println("Помилка: введи правильне ціле число.");
            }
        }
    }

    // метод для безпечного читання double з перевіркою діапазону
    private static double readDouble(Scanner sc, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().replace(',', '.');
            try {
                double value = Double.parseDouble(s);
                if (value < min || value > max) throw new IllegalArgumentException();
                return value;
            } catch (Exception e) {
                System.out.println("Помилка: введи число (можна з крапкою).");
            }
        }
    }
}