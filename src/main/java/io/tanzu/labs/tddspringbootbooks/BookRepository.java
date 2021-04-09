package io.tanzu.labs.tddspringbootbooks;

import java.util.List;

public interface BookRepository {

    List<Book> getAll();

    Book getBook(int id);

    Book add(NewBook newBook);

    Book update(int id, UpdateBook updateBook);

    void delete(int id);
}
