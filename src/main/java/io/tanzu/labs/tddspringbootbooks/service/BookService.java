package io.tanzu.labs.tddspringbootbooks.service;

import io.tanzu.labs.tddspringbootbooks.domain.Book;
import io.tanzu.labs.tddspringbootbooks.domain.NewBook;
import io.tanzu.labs.tddspringbootbooks.domain.UpdateBook;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBook(int id);

    Book addBook(NewBook newBook);

    Book updateBook(int id, UpdateBook updateBook);

    void deleteBook(int id);
}
