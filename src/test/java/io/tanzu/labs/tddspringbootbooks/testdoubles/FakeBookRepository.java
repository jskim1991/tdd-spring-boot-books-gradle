package io.tanzu.labs.tddspringbootbooks.testdoubles;

import io.tanzu.labs.tddspringbootbooks.domain.Book;
import io.tanzu.labs.tddspringbootbooks.repository.BookRepository;
import io.tanzu.labs.tddspringbootbooks.domain.NewBook;
import io.tanzu.labs.tddspringbootbooks.domain.UpdateBook;
import io.tanzu.labs.tddspringbootbooks.repository.exception.NoSuchBookException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeBookRepository implements BookRepository {

    private Map<Integer, Book> bookMap;

    public FakeBookRepository() {
        bookMap = new HashMap<>();
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Book getBook(int id) {
        throwExceptionIfBookDoesNotExist(id);
        return bookMap.get(id);
    }

    @Override
    public Book add(NewBook newBook) {
        Book book = new Book(bookMap.size() + 1, newBook);
        bookMap.put(book.getId(), book);
        return book;
    }

    @Override
    public Book update(int id, UpdateBook updateBook) {
        throwExceptionIfBookDoesNotExist(id);
        Book updatedBook = new Book(id, updateBook.getName());
        bookMap.put(id, updatedBook);
        return updatedBook;
    }

    @Override
    public void delete(int id) {
        throwExceptionIfBookDoesNotExist(id);
        bookMap.remove(id);
    }

    private void throwExceptionIfBookDoesNotExist(int id) {
        if (bookMap.containsKey(id) == false) {
            throw new NoSuchBookException("No such book for id " + id);
        }
    }
}
