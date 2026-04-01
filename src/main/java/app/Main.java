package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);

        System.out.println("=== Система обліку книг ===");

        while (true) {
             System.out.println("\n1. Додати книгу");
            System.out.println("2. Показати всі");
            System.out.println("3. Вихід");
            System.out.print("Вибір: ");

            String choice = sc.nextLine().trim();

            switch (choice) { 
                case "1": 
                 try { 
                        System.out.print("Назва: ");
                        String title = sc.nextLine().trim();

                        System.out.print("Автор: ");
                        String author = sc.nextLine().trim();

                        int year = readInt(sc, "Рік: ", 0, 3000);
                        double price = readDouble(sc, "Ціна: ", 0, 100000);

                        System.out.println("Жанр (FICTION, SCIENCE, HISTORY, FANTASY, DETECTIVE): ");
                        Genre genre = Genre.valueOf(sc.nextLine().trim().toUpperCase());

                        int pages = readInt(sc, "Сторінки: ", 1, 10000);

                        Book book = new Book(title, author, year, price, genre, pages);

                        // перевірка конструктора копіювання
                        Book copy = new Book(book);
                        System.out.println("Копія книги: " + copy);

                        System.out.println("Книга додана!");

                } catch (Exception e) {
                    System.out.println("Помилка: " + e.getMessage());
                }
                break;

                 case "2":
                    // На цьому етапі список книг не зберігається в колекції,
                    // тому нічого не виводимо. Пізніше буде ArrayList для всіх книг.
                    System.out.println("\n--- Список книг ---");
                    break;

                case "3":
                    System.out.println("До побачення!");
                    sc.close();
                    return;

               
                default:
                    System.out.println("Невірний вибір. Спробуй ще раз.");
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
            if (value < min || value > max) {
                System.out.println("Помилка: число повинно бути в діапазоні від " + min + " до " + max);
                continue; 
            }
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введи число (можна з крапкою).");
        }
        }
    }
}