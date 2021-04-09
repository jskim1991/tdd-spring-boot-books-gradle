package io.tanzu.labs.tddspringbootbooks;

import java.util.List;

public class SpyBookRepository implements BookRepository {

    private int getBook_argument_id;
    private int updateBook_argument_id;
    private UpdateBook updateBook_argument_updateBook;
    private NewBook addBook_argument_newBook;
    private int deleteBook_argument_id;

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getBook(int id) {
        this.getBook_argument_id = id;
        return null;
    }

    @Override
    public Book add(NewBook newBook) {
        addBook_argument_newBook = newBook;
        return null;
    }

    @Override
    public Book update(int id, UpdateBook updateBook) {
        this.updateBook_argument_id = id;
        this.updateBook_argument_updateBook = updateBook;
        return null;
    }

    @Override
    public void delete(int id) {
        deleteBook_argument_id = id;
    }

    public int getGetBook_argument_id() {
        return getBook_argument_id;
    }

    public int getUpdateBook_argument_id() {
        return updateBook_argument_id;
    }

    public UpdateBook getUpdateBook_argument_updateBook() {
        return updateBook_argument_updateBook;
    }

    public NewBook getAddBook_argument_book() {
        return addBook_argument_newBook;
    }

    public int getDeleteBook_argument_id() {
        return deleteBook_argument_id;
    }
}
