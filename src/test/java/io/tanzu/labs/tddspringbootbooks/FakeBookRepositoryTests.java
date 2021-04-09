package io.tanzu.labs.tddspringbootbooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FakeBookRepositoryTests {

    private FakeBookRepository fakeBookRepository;

    @BeforeEach
    void setup() {
        fakeBookRepository = new FakeBookRepository();
    }

    @Test
    void test_getAllBooks_returnsEmptyList() {
        assertThat(fakeBookRepository.getAll().isEmpty(), equalTo(true));
    }

    @Test
    void test_getAllBooks_returnsBooks() {
        fakeBookRepository.add(new NewBook("Book1"));


        List<Book> books = fakeBookRepository.getAll();


        assertThat(books.isEmpty(), equalTo(false));
        assertThat(books.get(0).getId(), equalTo(1));
        assertThat(books.get(0).getName(), equalTo("Book1"));
    }

    @Test
    void test_getBook_returnsBook() {
        fakeBookRepository.add(new NewBook("Book1"));


        Book actualBook = fakeBookRepository.getBook(1);


        assertThat(actualBook.getId(), equalTo(1));
        assertThat(actualBook.getName(), equalTo("Book1"));
    }

    @Test
    void test_getBook_throwsException() {
        Executable action = () -> fakeBookRepository.getBook(999);


        RuntimeException thrownException = assertThrows(RuntimeException.class, action);
        assertThat(thrownException.getMessage(), equalTo("No such book for id 999"));
    }

    @Test
    void test_updateBookAndGetBook_returnsUpdatedBook() {
        fakeBookRepository.add(new NewBook("Book1"));


        Book updatedBook = fakeBookRepository.update(1, new UpdateBook("Updated Book1"));
        Book actualBook = fakeBookRepository.getBook(1);


        assertThat(actualBook.getId(), equalTo(1));
        assertThat(actualBook.getName(), equalTo("Updated Book1"));

        assertThat(updatedBook.getId(), equalTo(1));
        assertThat(updatedBook.getName(), equalTo("Updated Book1"));
    }

    @Test
    void test_updateBookWhichDoesNotExist_throwsException() {
        RuntimeException thrownException = assertThrows(RuntimeException.class, () ->
                fakeBookRepository.update(999, new UpdateBook("Updated Book1")));
        assertThat(thrownException.getMessage(), equalTo("No book to update for id 999"));
    }

    // maybe be better to split into 2 test cases
    @Test
    void test_addBookAndGetBook_returnsAddedBook() {
        Book addedBook = fakeBookRepository.add(new NewBook("Book1"));
        Book actualBook = fakeBookRepository.getBook(1);


        assertThat(addedBook.getId(), equalTo(1));
        assertThat(addedBook.getName(), equalTo("Book1"));

        assertThat(actualBook.getId(), equalTo(1));
        assertThat(actualBook.getName(), equalTo("Book1"));
    }

    @Test
    void test_deleteBook_deletesBookWithGivenId() {
        fakeBookRepository.add(new NewBook("Book1"));


        fakeBookRepository.delete(1);


        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> fakeBookRepository.getBook(1));
        assertThat(thrownException.getMessage(), equalTo("No such book for id 1"));
    }

    @Test
    void test_deleteBookWhichDoesNotExist_throwsException() {
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> fakeBookRepository.delete(999));
        assertThat(thrownException.getMessage(), equalTo("No book to delete for id 999"));
    }
}
