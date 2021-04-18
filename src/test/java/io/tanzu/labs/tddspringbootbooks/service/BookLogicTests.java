package io.tanzu.labs.tddspringbootbooks.service;

import io.tanzu.labs.tddspringbootbooks.domain.Book;
import io.tanzu.labs.tddspringbootbooks.domain.NewBook;
import io.tanzu.labs.tddspringbootbooks.domain.UpdateBook;
import io.tanzu.labs.tddspringbootbooks.repository.exception.NoSuchBookException;
import io.tanzu.labs.tddspringbootbooks.testdoubles.FakeBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookLogicTests {

    private FakeBookRepository fakeBookRepository;
    private BookLogic bookLogic;

    @BeforeEach
    void setUp() {
        fakeBookRepository = new FakeBookRepository();
        bookLogic = new BookLogic(fakeBookRepository);
    }

    @Test
    void test_getAllBooks_returnsBookList() {
        fakeBookRepository.add(new NewBook("Book1"));


        List<Book> allBooks = bookLogic.getAllBooks();


        Book actualBook = allBooks.get(0);
        assertThat(actualBook.getId(), equalTo(1));
        assertThat(actualBook.getName(), equalTo("Book1"));
    }

    @Test
    void test_getAllBooksWhenEmpty_returnsEmptyList() {
        assertThat(bookLogic.getAllBooks().isEmpty(), equalTo(true));
    }

    @Test
    void test_getBook_returnsBook() {
        fakeBookRepository.add(new NewBook("Book1"));


        Book actualBook = bookLogic.getBook(1);


        assertThat(actualBook.getId(), equalTo(1));
        assertThat(actualBook.getName(), equalTo("Book1"));
    }

    @Test
    void test_getBookWhenEmpty_throwsException() {
        NoSuchBookException thrownException = assertThrows(NoSuchBookException.class,
                () -> bookLogic.getBook(999));
        assertThat(thrownException.getMessage(), equalTo("No such book for id 999"));
    }

    @Test
    void test_addBook_returnsBook() {
        Book book = bookLogic.addBook(new NewBook("Book1"));


        assertThat(book.getId(), equalTo(1));
        assertThat(book.getName(), equalTo("Book1"));
    }

    @Test
    void test_updatedBook_returnsBook() {
        fakeBookRepository.add(new NewBook("Book1"));


        Book book = bookLogic.updateBook(1, new UpdateBook("Updated Book1"));


        assertThat(book.getId(), equalTo(1));
        assertThat(book.getName(), equalTo("Updated Book1"));
    }

    @Test
    void test_updateBookWhenEmpty_throwsException() {
        NoSuchBookException thrownException = assertThrows(NoSuchBookException.class,
                () -> bookLogic.updateBook(999, new UpdateBook("Updated Book1")));
        assertThat(thrownException.getMessage(), equalTo("No such book for id 999"));
    }

    @Test
    void test_deleteBook_deletesBook() {
        fakeBookRepository.add(new NewBook("Book1"));


        bookLogic.deleteBook(1);


        assertThat(bookLogic.getAllBooks().isEmpty(), equalTo(true));
    }

    @Test
    void test_deleteBookWhenEmpty_throwsException() {
        NoSuchBookException thrownException = assertThrows(NoSuchBookException.class,
                () -> bookLogic.deleteBook(999));
        assertThat(thrownException.getMessage(), equalTo("No such book for id 999"));
    }
}
