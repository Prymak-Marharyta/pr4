package app;

import app.exceptions.InvalidFieldValueException;
import app.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LibraryExceptionTest {

    @Test
    void shouldThrowInvalidFieldValueExceptionWhenTitleIsEmpty() {
        assertThrows(InvalidFieldValueException.class, () -> {
            new PaperBook("", "Author", 2020, 100.0, Genre.FICTION, 250, "Hardcover");
        });
    }

    @Test
    void shouldThrowObjectNotFoundExceptionWhenDeletingNonExistingBook() {
        Library library = new Library();

        Book book = new PaperBook("Test Book", "Author", 2020, 100.0, Genre.FICTION, 250, "Hardcover");

        assertThrows(ObjectNotFoundException.class, () -> {
            library.delete(book);
        });
    }

    @Test
    void shouldThrowObjectNotFoundExceptionWhenUpdatingNonExistingBook() {
        Library library = new Library();

        Book oldBook = new PaperBook("Old Book", "Author", 2020, 100.0, Genre.FICTION, 250, "Hardcover");
        Book newBook = new PaperBook("New Book", "Author", 2021, 120.0, Genre.FANTASY, 300, "Softcover");

        assertThrows(ObjectNotFoundException.class, () -> {
            library.update(oldBook, newBook);
        });
    }
}