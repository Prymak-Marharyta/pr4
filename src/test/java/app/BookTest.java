package app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Book book = new Book("Valid", "Author", 2020, 100.0, "Fiction", 200);

        assertThrows(IllegalArgumentException.class, () -> book.setYear(-1));
        assertThrows(IllegalArgumentException.class, () -> book.setPrice(-50.0));
        assertThrows(IllegalArgumentException.class, () -> book.setTitle(""));
        assertThrows(IllegalArgumentException.class, () -> book.setPages(0));
    }

    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book("", "", -5, -100, "", 0));
    }
}