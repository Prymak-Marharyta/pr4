package app;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Book> loadedBooks = FileManager.loadFromFile("input.txt");
        
        Library library = new Library();

        for (Book b : loadedBooks) {
            library.addNewBook(b, 1);
        }

        System.out.println("=== Система облiку книг ===");

         while (true) {
            System.out.println("\n=== Головне меню ===");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести всi книги");
            System.out.println("3. Пошук");
            System.out.println("4. Завершити роботу");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    createNewObjectMenu(sc, library);
                    break;

                case "2":
                    library.printAll();
                    break;

                case "3":
                    searchMenu(sc, library);
                    break;

                case "4":
                    FileManager.saveToFile(library.toBookList(), "input.txt");
                    System.out.println("Данi збережено!");
                    return;

                default:
                    System.out.println("Невiрний вибiр");
            }
        }
    }

    // меню створення об’єкта
    private static void createNewObjectMenu(Scanner sc, Library library) {

        System.out.println("Оберiть тип книги:");
        System.out.println("1. Book");
        System.out.println("2. EBook");
        System.out.println("3. PaperBook");
        System.out.println("4. AudioBook");
        System.out.println("5. UsedBook");

        String type = sc.nextLine();

        System.out.print("Назва: ");
        String title = sc.nextLine();

        System.out.print("Автор: ");
        String author = sc.nextLine();

        int year = readInt(sc, "Рiк: ", 0, 3000);
        double price = readDouble(sc, "Цiна: ", 0, 100000);

        Genre genre = null;
        while (genre == null) {
            try {
                System.out.println("Жанр (FICTION, SCIENCE, HISTORY, FANTASY, DETECTIVE): ");
                genre = Genre.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (Exception e) {
                System.out.println("Помилка жанру");
            }
        }

        int pages = readInt(sc, "Сторiнки: ", 1, 10000);

     switch (type) {

        case "1": {
            Book book = new Book(title, author, year, price, genre, pages);
            library.addNewBook(book, 1);
            break;
        }

        case "2": {
            double size = readDouble(sc, "Розмiр файлу: ", 0, 10000);
            Book book = new EBook(title, author, year, price, genre, pages, size);
            library.addNewBook(book, 1);
            break;
        }

        case "3": {
            System.out.print("Тип обкладинки: ");
            String cover = sc.nextLine();
            Book book = new PaperBook(title, author, year, price, genre, pages, cover);
            library.addNewBook(book, 1);
            break;
        }

        case "4": {
            int duration = readInt(sc, "Тривалiсть: ", 1, 10000);
            System.out.print("Диктор: ");
            String narrator = sc.nextLine();
            Book book = new AudioBook(title, author, year, price, genre, pages, duration, narrator);
            library.addNewBook(book, 1);
            break;
        }

        case "5": {
            System.out.print("Стан: ");
            String condition = sc.nextLine();
            double discount = readDouble(sc, "Знижка: ", 0, 100);
            Book book = new UsedBook(title, author, year, price, genre, pages, condition, discount);
            library.addNewBook(book, 1);
            break;
        }

        default:
            System.out.println("Невiрний вибiр");
    }

    System.out.println("Книгу додано!");
}

private static void searchMenu(Scanner sc, Library library) {

    while (true) {
        System.out.println("\n--- Пошук ---");
        System.out.println("1. За назвою");
        System.out.println("2. За автором");
        System.out.println("3. За роком");
        System.out.println("0. Назад");

        String choice = sc.nextLine().trim();

        switch (choice) {
            case "0":
                return;

            case "1":
                System.out.print("Введiть назву: ");
                printResults(library.searchByTitle(sc.nextLine()));
                break;

            case "2":
                System.out.print("Введiть автора: ");
                printResults(library.searchByAuthor(sc.nextLine()));
                break;

            case "3":
                int year = readInt(sc, "Введiть рiк: ", 0, 3000);
                printResults(library.searchByYear(year));
                break;

            default:
                System.out.println("Невiрний вибiр");
        }
    }
}

    private static void printResults(ArrayList<Book> result) {
        if (result.isEmpty()) {
            System.out.println("Нiчого не знайдено");
        } else {
            for (Book b : result) {
                System.out.println(b);
            }
        }
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