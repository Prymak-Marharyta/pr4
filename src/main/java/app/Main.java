package app;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Book> books = JsonManager.load("input.json");

        System.out.println("=== Система облiку книг ===");

        while (true) {
            System.out.println("\n=== Головне меню ===");
            System.out.println("1. Створити новий об’єкт");
            System.out.println("2. Вивести iнформацiю про всi об’єкти");
            System.out.println("3. Пошук об’єкта");
            System.out.println("4. Завершити роботу програми");

            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        createNewObjectMenu(sc, books);
                        break;

                    case "2":
                        System.out.println("\n--- Список книг ---");
                        if (books.isEmpty()) {
                            System.out.println("Список порожнiй.");
                        } else {
                            for (Book b : books) {
                                System.out.println(b); // ПОЛIФОРМIЗМ
                            }
                        }
                        break;

                    case "3":
                        searchMenu(sc, books);
                        break;

                    case "4":
                        FileManager.saveToFile(books, "input.txt");
                        System.out.println("Дані збережено!");
                        return;
                    default:
                        System.out.println("Невiрний вибiр");
                }

            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }

    // меню створення об’єкта
    private static void createNewObjectMenu(Scanner sc, ArrayList<Book> books) {
        while (true) {
            System.out.println("\n--- Створення нового об’єкта ---");
            System.out.println("1. Book");
            System.out.println("2. EBook");
            System.out.println("3. PaperBook");
            System.out.println("4. AudioBook");
            System.out.println("5. UsedBook");
            System.out.println("0. Назад у головне меню");

            String typeChoice = sc.nextLine().trim();

            switch (typeChoice) {
                case "0":
                    return;

                case "1": {
                    Book book = createBaseBook(sc);
                    books.add(book);
                    System.out.println("Додано Book");
                    return;
                }

                case "2": {
                    Book base = createBaseBook(sc);
                    double fileSize = readDouble(sc, "Розмiр файлу (MB): ", 0, 10000);

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
                    return;
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
                    return;
                }

                case "4": {
                    Book base = createBaseBook(sc);

                    int duration = readInt(sc, "Тривалiсть (хв): ", 1, 10000);

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
                    return;
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
                    return;
                }

                default:
                    System.out.println("Невiрний вибiр типу. Спробуйте ще раз.");
            }
        }
    }

    // cтворення базового об'єкта Book
    private static Book createBaseBook(Scanner sc) {
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
                System.out.println("Невiрний жанр. Спробуйте ще раз.");
            }
        }

        int pages = readInt(sc, "Сторiнки: ", 1, 10000);

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

    private static void searchMenu(Scanner sc, ArrayList<Book> books) {
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
                System.out.print("Введіть назву: ");
                String title = sc.nextLine();
                printResults(searchByTitle(books, title));
                break;

            case "2":
                System.out.print("Введіть автора: ");
                String author = sc.nextLine();
                printResults(searchByAuthor(books, author));
                break;

            case "3":
                int year = readInt(sc, "Введіть рік: ", 0, 3000);
                printResults(searchByYear(books, year));
                break;

            default:
                System.out.println("Невірний вибір");
        }
    }
}

    private static ArrayList<Book> searchByTitle(ArrayList<Book> books, String title) {
        ArrayList<Book> result = new ArrayList<>();

        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
            result.add(b);
            }
        }

        return result;
    }

    private static ArrayList<Book> searchByAuthor(ArrayList<Book> books, String author) {
        ArrayList<Book> result = new ArrayList<>();

        for (Book b : books) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
            result.add(b);
            }
        }

    return result;
    }

    private static ArrayList<Book> searchByYear(ArrayList<Book> books, int year) {
        ArrayList<Book> result = new ArrayList<>();

        for (Book b : books) {
            if (b.getYear() == year) {
            result.add(b);
            }
        }

    return result;
    }

    private static void printResults(ArrayList<Book> result) {
        if (result.isEmpty()) {
            System.out.println("Нічого не знайдено");
        } else {
            System.out.println("\n--- Результати пошуку ---");
            for (Book b : result) {
                System.out.println(b);
            }
        }
    }
}