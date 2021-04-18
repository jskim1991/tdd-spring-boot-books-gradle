package io.tanzu.labs.tddspringbootbooks.service;

import io.tanzu.labs.tddspringbootbooks.domain.Book;
import io.tanzu.labs.tddspringbootbooks.domain.NewBook;
import io.tanzu.labs.tddspringbootbooks.domain.UpdateBook;
import io.tanzu.labs.tddspringbootbooks.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookLogic implements BookService {

    private BookRepository bookRepository;

    public BookLogic(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    @Override
    public Book getBook(int id) {
        return bookRepository.getBook(id);
    }

    @Override
    public Book addBook(NewBook newBook) {
        return bookRepository.add(newBook);
    }

    @Override
    public Book updateBook(int id, UpdateBook updateBook) {
        return bookRepository.update(id, updateBook);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.delete(id);
    }
}
