package io.tanzu.labs.tddspringbootbooks;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalBookRepository implements BookRepository {

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getBook(int id) {
        return null;
    }

    @Override
    public Book add(NewBook newBook) {

        return null;
    }

    @Override
    public Book update(int id, UpdateBook updateBook) {

        return null;
    }

    @Override
    public void delete(int id) {

    }

}
