package io.tanzu.labs.tddspringbootbooks;

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
        throwExceptionIfBookDoesNotExist(id, "No such book for id ");
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
        throwExceptionIfBookDoesNotExist(id, "No book to update for id ");
        Book updatedBook = new Book(id, updateBook.getName());
        bookMap.put(id, updatedBook);
        return updatedBook;
    }

    @Override
    public void delete(int id) {
        throwExceptionIfBookDoesNotExist(id, "No book to delete for id ");
        bookMap.remove(id);
    }

    private void throwExceptionIfBookDoesNotExist(int id, String exceptionMessage) {
        if (bookMap.containsKey(id) == false) {
            throw new RuntimeException(exceptionMessage + id);
        }
    }
}
