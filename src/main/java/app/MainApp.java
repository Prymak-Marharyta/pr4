package app;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Library library = new Library();

    private ComboBox<String> typeBox;
    private ComboBox<Genre> genreBox;

    private TextField titleField;
    private TextField authorField;
    private TextField yearField;
    private TextField priceField;
    private TextField pagesField;

    private TextField extraField1;
    private TextField extraField2;

    private Label extraLabel1;
    private Label extraLabel2;

    private TextArea collectionArea;
    private TextField uuidSearchField;
    private TextArea resultArea;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Система облiку книг");

        ArrayList<Book> loadedBooks = FileManager.loadFromFile("input.txt");
            for (Book b : loadedBooks) {
                library.addNewBook(b, 1);
            }

        // тип книги
        typeBox = new ComboBox<>();
        typeBox.getItems().addAll("EBook", "PaperBook", "AudioBook", "UsedBook");
        typeBox.setValue("EBook");

        // жанр книги
        genreBox = new ComboBox<>();
        genreBox.getItems().addAll(Genre.values());
        genreBox.setValue(Genre.FICTION);

        titleField = new TextField();
        authorField = new TextField();
        yearField = new TextField();
        priceField = new TextField();
        pagesField = new TextField();

        extraField1 = new TextField();
        extraField2 = new TextField();

        extraLabel1 = new Label();
        extraLabel2 = new Label();

        extraLabel1.setMinWidth(130);
        extraLabel2.setMinWidth(130);

        collectionArea = new TextArea();
        collectionArea.setEditable(false);
        collectionArea.setPrefHeight(200);

        uuidSearchField = new TextField();

        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefHeight(150);

        // форма
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10));

        form.add(new Label("Тип:"), 0, 0);
        form.add(typeBox, 1, 0);

        form.add(new Label("Назва:"), 0, 1);
        form.add(titleField, 1, 1);

        form.add(new Label("Автор:"), 0, 2);
        form.add(authorField, 1, 2);

        form.add(new Label("Рiк:"), 0, 3);
        form.add(yearField, 1, 3);

        form.add(new Label("Цiна:"), 0, 4);
        form.add(priceField, 1, 4);

        form.add(new Label("Сторiнки:"), 0, 5);
        form.add(pagesField, 1, 5);

        // жанр у форму
        form.add(new Label("Жанр:"), 0, 6);
        form.add(genreBox, 1, 6);

        form.add(extraLabel1, 0, 7);
        form.add(extraField1, 1, 7);

        form.add(extraLabel2, 0, 8);
        form.add(extraField2, 1, 8);

        updateExtraFields();

        typeBox.setOnAction(e -> updateExtraFields());

        Button addButton = new Button("Додати");
        addButton.setOnAction(e -> addBook());

        VBox leftBlock = new VBox(10,
                new Label("Додавання книги"),
                form,
                addButton
        );
        leftBlock.setPadding(new Insets(10));

        VBox centerBlock = new VBox(10,
                new Label("Список книг (коротко)"),
                collectionArea
        );
        centerBlock.setPadding(new Insets(10));

        Button searchButton = new Button("Знайти");
        searchButton.setOnAction(e -> searchByUuid());

        VBox rightBlock = new VBox(10,
                new Label("Пошук за UUID"),
                new Label("UUID:"),
                uuidSearchField,
                searchButton,
                new Label("Результат"),
                resultArea
        );
        rightBlock.setPadding(new Insets(10));

        HBox root = new HBox(15, leftBlock, centerBlock, rightBlock);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 1200, 500);
        stage.setScene(scene);
        stage.show();

        refreshCollectionArea();
    }

    private void updateExtraFields() {
        String type = typeBox.getValue();

        if ("EBook".equals(type)) {
            extraLabel1.setText("Розмiр файлу:");

            extraLabel2.setText("");
            extraLabel2.setVisible(false);
            extraField2.setVisible(false);

        } else if ("PaperBook".equals(type)) {
            extraLabel1.setText("Тип обкладинки:");

            extraLabel2.setText("");
            extraLabel2.setVisible(false);
            extraField2.setVisible(false);

        } else if ("AudioBook".equals(type)) {
            extraLabel1.setText("Тривалiсть:");
            extraLabel2.setText("Диктор:");

            extraLabel2.setVisible(true);
            extraField2.setVisible(true);

        } else if ("UsedBook".equals(type)) {
            extraLabel1.setText("Стан:");
            extraLabel2.setText("Знижка:");

            extraLabel2.setVisible(true);
            extraField2.setVisible(true);
        }
    }

    private void addBook() {
        try {
            String type = typeBox.getValue();

            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            int year = Integer.parseInt(yearField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim().replace(',', '.'));
            int pages = Integer.parseInt(pagesField.getText().trim());

            Genre genre = genreBox.getValue();

            Book book;

            switch (type) {
                case "EBook":
                    double size = Double.parseDouble(extraField1.getText().trim().replace(',', '.'));
                    book = new EBook(title, author, year, price, genre, pages, size);
                    break;

                case "PaperBook":
                    String cover = extraField1.getText().trim();
                    book = new PaperBook(title, author, year, price, genre, pages, cover);
                    break;

                case "AudioBook":
                    int duration = Integer.parseInt(extraField1.getText().trim());
                    String narrator = extraField2.getText().trim();
                    book = new AudioBook(title, author, year, price, genre, pages, duration, narrator);
                    break;

                case "UsedBook":
                    String condition = extraField1.getText().trim();
                    double discount = Double.parseDouble(extraField2.getText().trim().replace(',', '.'));
                    book = new UsedBook(title, author, year, price, genre, pages, condition, discount);
                    break;

                default:
                    showAlert("Помилка", "Невiдомий тип книги");
                    return;
            }

            library.addNewBook(book, 1);

            refreshCollectionArea();
            clearFields();

            resultArea.setText("Книгу додано\nUUID: " + book.getUuid());

        } catch (NumberFormatException e) {
            showAlert("Помилка", "Некоректний числовий формат");
        } catch (IllegalArgumentException e) {
            showAlert("Помилка", e.getMessage());
        } catch (Exception e) {
            showAlert("Помилка", "Не вдалося додати книгу");
        }
    }

    private void searchByUuid() {
        String uuidText = uuidSearchField.getText().trim();

        try {
            Book found = library.searchByUuid(uuidText);

            if (found == null) {
                resultArea.setText("Нiчого не знайдено");
            } else {
                resultArea.setText(found.toString());
            }

        } catch (IllegalArgumentException e) {
            resultArea.setText("Некоректний формат UUID");
        }
    }

    private void refreshCollectionArea() {
        StringBuilder sb = new StringBuilder();

        for (Book book : library.getAllBooks()) {
            sb.append(book.toShortString()).append("\n");
        }

        collectionArea.setText(sb.toString());
    }

    private void clearFields() {
        titleField.clear();
        authorField.clear();
        yearField.clear();
        priceField.clear();
        pagesField.clear();
        extraField1.clear();
        extraField2.clear();
    }

    private void showAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}