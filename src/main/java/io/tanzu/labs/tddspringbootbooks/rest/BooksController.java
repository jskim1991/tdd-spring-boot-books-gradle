package io.tanzu.labs.tddspringbootbooks.rest;

import io.tanzu.labs.tddspringbootbooks.domain.Book;
import io.tanzu.labs.tddspringbootbooks.domain.NewBook;
import io.tanzu.labs.tddspringbootbooks.domain.UpdateBook;
import io.tanzu.labs.tddspringbootbooks.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return bookService.getBook(id);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Book addBook(@RequestBody NewBook newBook) {
        return bookService.addBook(newBook);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody UpdateBook updateBook) {
        return bookService.updateBook(id, updateBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }

}
