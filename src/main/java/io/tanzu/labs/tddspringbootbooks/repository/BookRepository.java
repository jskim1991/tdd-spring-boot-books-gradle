package io.tanzu.labs.tddspringbootbooks.repository;

import io.tanzu.labs.tddspringbootbooks.domain.Book;
import io.tanzu.labs.tddspringbootbooks.domain.NewBook;
import io.tanzu.labs.tddspringbootbooks.domain.UpdateBook;

import java.util.List;

public interface BookRepository {

    List<Book> getAll();

    Book getBook(int id);

    Book add(NewBook newBook);

    Book update(int id, UpdateBook updateBook);

    void delete(int id);
}
