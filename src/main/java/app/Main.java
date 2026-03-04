package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Book[] books = new Book[5];

        books[0] = new Book("1984", "George Orwell", 1949, 250.0);
        books[1] = new Book("Fahrenheit 451", "Ray Bradbury", 1953, 220.0);

        for (int i = 2; i < books.length; i++) {
            System.out.println("\nВведи дані для книги №" + (i + 1));

            System.out.print("Назва: ");
            String title = sc.nextLine().trim();

            System.out.print("Автор: ");
            String author = sc.nextLine().trim();

            int year = readInt(sc, "Рік видання: ", 0, 3000);
            double price = readDouble(sc, "Ціна: ", 0.0, 1_000_000.0);

            books[i] = new Book(title, author, year, price);
        }

        System.out.println("\n--- Усі книги ---");
        for (Book b : books) {
            System.out.println(b);
        }

        System.out.println("\n--- Перевірка equals ---");
        Book copy = new Book("1984", "George Orwell", 1949, 250.0);
        System.out.println("books[0] equals copy? " + books[0].equals(copy));

        sc.close();
    }

    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(s);
                if (value < min || value > max) {
                    System.out.println("Помилка: число має бути в діапазоні [" + min + "; " + max + "].");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введи ціле число.");
            }
        }
    }

    private static double readDouble(Scanner sc, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().replace(',', '.');
            try {
                double value = Double.parseDouble(s);
                if (value < min || value > max) {
                    System.out.println("Помилка: число має бути в діапазоні [" + min + "; " + max + "].");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введи число (можна з крапкою).");
            }
        }
    }
}