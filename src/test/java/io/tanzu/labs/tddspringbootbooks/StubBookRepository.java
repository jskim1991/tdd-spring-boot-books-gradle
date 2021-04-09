package io.tanzu.labs.tddspringbootbooks;

import java.util.ArrayList;
import java.util.List;

public class StubBookRepository implements BookRepository {

    private List<Book> data;
    private Book getBook_returnValue;
    private Book updateBook_returnValue;
    private Book addBook_returnValue;

    public StubBookRepository() {
        data = new ArrayList<>();
    }

    @Override
    public List<Book> getAll() {
        return data;
    }

    @Override
    public Book getBook(int id) {
        return getBook_returnValue;
    }

    @Override
    public Book add(NewBook newBook) {
        return addBook_returnValue;
    }

    @Override
    public Book update(int id, UpdateBook updateBook) {
        return updateBook_returnValue;
    }

    @Override
    public void delete(int id) {

    }

    public void setData(List<Book> data) {
        this.data = data;
    }

    public void setGetBook_returnValue(Book book) {
        this.getBook_returnValue = book;
    }

    public void setUpdateBook_returnValue(Book book) {
        this.updateBook_returnValue = book;
    }

    public void setAddBook_returnValue(Book book) {
        this.addBook_returnValue = book;
    }
}
