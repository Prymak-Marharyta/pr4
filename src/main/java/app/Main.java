package app;

import app.exceptions.InvalidFieldValueException;
import app.exceptions.ObjectNotFoundException;

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
            System.out.println("3. Модифiкувати книгу");
            System.out.println("4. Видалити книгу");
            System.out.println("5. Пошук");
            System.out.println("6. Вивести вiдсортованi книги");
            System.out.println("7. Завершити роботу");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    createNewObjectMenu(sc, library);
                    break;

                case "2":
                    library.printAllWithNumbers();
                    break;

                case "3":
                    updateBookMenu(sc, library);
                    break;

                case "4":
                    deleteBookMenu(sc, library);
                    break;

                case "5":
                    searchMenu(sc, library);
                    break;

                case "6":
                    sortMenu(sc, library);
                    break;

                case "7":
                    FileManager.saveToFile(library.toBookList(), "input.txt");
                    System.out.println("Данi збережено!");
                    return;

                default:
                    System.out.println("Неправильний вибiр");
            }
        }
    }

    // меню створення об’єкта
    private static void createNewObjectMenu(Scanner sc, Library library) {

        System.out.println("Оберiть тип книги:");
        System.out.println("1. EBook");
        System.out.println("2. PaperBook");
        System.out.println("3. AudioBook");
        System.out.println("4. UsedBook");

        String type = sc.nextLine();

        System.out.print("Назва: ");
        String title = sc.nextLine();

        System.out.print("Автор: ");
        String author = sc.nextLine();

        int year = readInt(sc, "Рiк: ", 0, 3000);
        double price = readDouble(sc, "Цiна: ", 0, 100000);

        Genre genre = readGenre(sc);
        
        int pages = readInt(sc, "Сторiнки: ", 1, 10000);

        Book book;

        try {
        switch (type) {

        case "1": {
            double size = readDouble(sc, "Розмiр файлу: ", 0, 10000);
            book = new EBook(title, author, year, price, genre, pages, size);
            break;
        }

        case "2": {
            System.out.print("Тип обкладинки: ");
            String cover = sc.nextLine();
            book = new PaperBook(title, author, year, price, genre, pages, cover);
            break;
        }

        case "3": {
            int duration = readInt(sc, "Тривалiсть: ", 1, 10000);
            System.out.print("Диктор: ");
            String narrator = sc.nextLine();
            book = new AudioBook(title, author, year, price, genre, pages, duration, narrator);
            break;
        }

        case "4": {
            System.out.print("Стан: ");
            String condition = sc.nextLine();
            double discount = readDouble(sc, "Знижка: ", 0, 100);
            book = new UsedBook(title, author, year, price, genre, pages, condition, discount);
            break;
        }

        default:
            System.out.println("Неправильний вибiр");
            return;
    }

    library.addNewBook(book, 1);
    System.out.println("Книгу додано!");

        } catch (InvalidFieldValueException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void updateBookMenu(Scanner sc, Library library) {

        if (library.isEmpty()) {
            System.out.println("Бiблiотека порожня. Немає що модифiкувати.");
            return;
        }

        System.out.println("\n--- Модифiкацiя книги ---");
        library.printAllWithNumbers();

        try {
        int number = readInt(sc, "Оберiть номер книги: ", 1, library.getItemsCount());
        Book oldBook = library.getBookByNumber(number);

        Book newBook = createUpdatedBook(sc, oldBook);

        if (newBook == null) {
            System.out.println("Модифiкацiю скасовано.");
            return;
        }

        library.update(oldBook, newBook);
        System.out.println("Книгу успiшно модифiковано!");

        } catch (ObjectNotFoundException | InvalidFieldValueException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
private static Book createUpdatedBook(Scanner sc, Book oldBook) {

    String title = oldBook.getTitle();
    String author = oldBook.getAuthor();
    int year = oldBook.getYear();
    double price = oldBook.getPrice();
    Genre genre = oldBook.getGenre();
    int pages = oldBook.getPages();

    System.out.println("\nОберiть атрибут для змiни:");
    System.out.println("1. Назва");
    System.out.println("2. Автор");
    System.out.println("3. Рiк");
    System.out.println("4. Цiна");
    System.out.println("5. Жанр");
    System.out.println("6. Кiлькiсть сторiнок");

    if (oldBook instanceof EBook) {
        System.out.println("7. Розмiр файлу");
    } else if (oldBook instanceof PaperBook) {
        System.out.println("7. Тип обкладинки");
    } else if (oldBook instanceof AudioBook) {
        System.out.println("7. Тривалiсть");
        System.out.println("8. Диктор");
    } else if (oldBook instanceof UsedBook) {
        System.out.println("7. Стан");
        System.out.println("8. Знижка");
    }

    String choice = sc.nextLine().trim();

    if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") &&
        !choice.equals("4") && !choice.equals("5") && !choice.equals("6") &&
        !choice.equals("7") && !choice.equals("8")) {
        System.out.println("Неправильний вибiр");
        return null;
    }

    switch (choice) {
        case "1":
            System.out.print("Нова назва: ");
            title = sc.nextLine();
            break;

        case "2":
            System.out.print("Новий автор: ");
            author = sc.nextLine();
            break;

        case "3":
            year = readInt(sc, "Новий рiк: ", 0, 3000);
            break;

        case "4":
            price = readDouble(sc, "Нова цiна: ", 0, 100000);
            break;

        case "5":
            genre = readGenre(sc);
            break;

        case "6":
            pages = readInt(sc, "Нова кiлькiсть сторiнок: ", 1, 10000);
            break;
    }

    if (oldBook instanceof EBook) {
        EBook oldEBook = (EBook) oldBook;
        double fileSize = oldEBook.getFileSize();

        if (choice.equals("7")) {
            fileSize = readDouble(sc, "Новий розмiр файлу: ", 0, 10000);
        }

        return new EBook(title, author, year, price, genre, pages, fileSize);
    }

    if (oldBook instanceof PaperBook) {
        PaperBook oldPaperBook = (PaperBook) oldBook;
        String coverType = oldPaperBook.getCoverType();

        if (choice.equals("7")) {
            System.out.print("Новий тип обкладинки: ");
            coverType = sc.nextLine();
        }

        return new PaperBook(title, author, year, price, genre, pages, coverType);
    }

    if (oldBook instanceof AudioBook) {
        AudioBook oldAudioBook = (AudioBook) oldBook;
        int duration = oldAudioBook.getDuration();
        String narrator = oldAudioBook.getNarrator();

        if (choice.equals("7")) {
            duration = readInt(sc, "Нова тривалiсть: ", 1, 10000);
        } else if (choice.equals("8")) {
            System.out.print("Новий диктор: ");
            narrator = sc.nextLine();
        }

        return new AudioBook(title, author, year, price, genre, pages, duration, narrator);
    }

    if (oldBook instanceof UsedBook) {
        UsedBook oldUsedBook = (UsedBook) oldBook;
        String condition = oldUsedBook.getCondition();
        double discount = oldUsedBook.getDiscount();

        if (choice.equals("7")) {
            System.out.print("Новий стан: ");
            condition = sc.nextLine();
        } else if (choice.equals("8")) {
            discount = readDouble(sc, "Нова знижка: ", 0, 100);
        }

        return new UsedBook(title, author, year, price, genre, pages, condition, discount);
    }

        return oldBook;
    }

    private static void deleteBookMenu(Scanner sc, Library library) {

        if (library.isEmpty()) {
            System.out.println("Бiблiотека порожня. Нема що видаляти.");
            return;
        }

        System.out.println("\n--- Видалення книги ---");
        library.printAllWithNumbers();

        try {
            int number = readInt(sc, "Оберiть номер книги: ", 1, library.getItemsCount());
            Book book = library.getBookByNumber(number);

            System.out.print("Ви дiйсно хочете видалити цю книгу? (yes/no): ");
            String answer = sc.nextLine().trim();

            if (!answer.equalsIgnoreCase("yes")) {
                System.out.println("Видалення скасовано.");
                return;
            }

            library.delete(book);
            System.out.println("Книгу успiшно видалено!");

        } catch (ObjectNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
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
                System.out.println("Неправильний вибiр");
        }
    }
}

    private static void sortMenu(Scanner sc, Library library) {

    while (true) {
        System.out.println("\n--- Сортування ---");
        System.out.println("1. За назвою");
        System.out.println("2. За роком");
        System.out.println("3. За цiною");
        System.out.println("0. Назад");

        String choice = sc.nextLine().trim();

        switch (choice) {
            case "0":
                return;

            case "1":
                library.printSortedByTitle();
                break;

            case "2":
                library.printSortedByYear();
                break;

            case "3":
                library.printSortedByPrice();
                break;

            default:
                System.out.println("Неправильний вибiр");
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

        private static Genre readGenre(Scanner sc) {
        Genre genre = null;

        while (genre == null) {
            try {
                System.out.println("Жанр (FICTION, SCIENCE, HISTORY, FANTASY, DETECTIVE): ");
                genre = Genre.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (Exception e) {
                System.out.println("Помилка жанру");
            }
        }

        return genre;
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