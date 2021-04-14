package io.tanzu.labs.tddspringbootbooks.repository;

import io.tanzu.labs.tddspringbootbooks.domain.Book;
import io.tanzu.labs.tddspringbootbooks.domain.NewBook;
import io.tanzu.labs.tddspringbootbooks.domain.UpdateBook;
import io.tanzu.labs.tddspringbootbooks.testdoubles.SpyStubBookJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalBookRepositoryTests {

    private SpyStubBookJpaRepository spyStubBookJpaRepository;
    private LocalBookRepository localBookRepository;

    @BeforeEach
    void setup() {
        spyStubBookJpaRepository = new SpyStubBookJpaRepository();
        localBookRepository = new LocalBookRepository(spyStubBookJpaRepository);
    }

    @Test
    void test_getAllWhenEmpty_returnsEmptyList() {
        assertThat(localBookRepository.getAll().isEmpty(), equalTo(true));
    }

    @Test
    void test_getAll_returnsBooks() {
        spyStubBookJpaRepository.setFindAll_returnValue(Collections.singletonList(new BookEntity(1, "Book1")));


        List<Book> returnedBooks = localBookRepository.getAll();


        assertThat(returnedBooks.size(), greaterThan(0));
        assertThat(returnedBooks.get(0).getName(), equalTo("Book1"));
        assertThat(returnedBooks.get(0).getId(), equalTo(1));
    }

    @Test
    void test_getBookWhenEmpty_throwsException() {
        RuntimeException thrownException = assertThrows(RuntimeException.class, () ->
                localBookRepository.getBook(123));
        assertThat(thrownException.getMessage(), equalTo("No such book for id 123"));
    }

    @Test
    void test_getBook_returnsBook() {
        spyStubBookJpaRepository.setFindById_returnValue(new BookEntity(1, "Book1"));


        Book book = localBookRepository.getBook(1);


        assertThat(book.getId(), equalTo(1));
        assertThat(book.getName(), equalTo("Book1"));
    }

    @Test
    void test_getBook_returnsCorrectId() {
        spyStubBookJpaRepository.setFindById_returnValue(new BookEntity(123, "Book1"));


        localBookRepository.getBook(123);


        assertThat(spyStubBookJpaRepository.getGetBook_argument_id(), equalTo(123));
    }

    @Test
    void test_addBook_returnsBook() {
        spyStubBookJpaRepository.setAdd_return_value(new BookEntity(1, "Book1"));


        Book returnedBook = localBookRepository.add(new NewBook("Book1"));


        assertThat(returnedBook.getId(), equalTo(1));
        assertThat(returnedBook.getName(), equalTo("Book1"));
    }

    @Test
    void test_updatedBookWhenEmpty_throwsException() {
        RuntimeException thrownException = assertThrows(RuntimeException.class, () ->
                localBookRepository.update(999, new UpdateBook("Updated Book1")));
        assertThat(thrownException.getMessage(), equalTo("No such book for id 999"));
    }

    @Test
    void test_updateBook_returnsUpdatedBook() {
        BookEntity book = new BookEntity(1, "Updated Book1");
        spyStubBookJpaRepository.setFindById_returnValue(book);
        spyStubBookJpaRepository.setUpdate_return_value(book);


        Book updatedBook = localBookRepository.update(1, new UpdateBook("Updated Book1"));


        assertThat(updatedBook.getId(), equalTo(1));
        assertThat(updatedBook.getName(), equalTo("Updated Book1"));
    }

    @Test
    void test_updatedBook_returnsCorrectId() {
        BookEntity book = new BookEntity(1, "Book1");
        spyStubBookJpaRepository.setFindById_returnValue(book);


        localBookRepository.update(1, new UpdateBook("Updated Book1"));


        assertThat(spyStubBookJpaRepository.getUpdateBook_argument_id(), equalTo(1));
    }

    @Test
    void test_deleteBookWhenEmpty_throwsException() {
        RuntimeException thrownException = assertThrows(RuntimeException.class,
                () -> localBookRepository.delete(999));
        assertThat(thrownException.getMessage(), equalTo("No such book for id 999"));
    }

    @Test
    void test_deleteBook_returnsCorrectId() {
        BookEntity book = new BookEntity(1, "Book1");
        spyStubBookJpaRepository.setFindById_returnValue(book);


        localBookRepository.delete(1);


        assertThat(spyStubBookJpaRepository.getDeleteBook_argument_id(), equalTo(1));
    }
}
