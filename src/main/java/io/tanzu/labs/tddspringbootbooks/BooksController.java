package io.tanzu.labs.tddspringbootbooks;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookRepository repository;

    public BooksController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Book> getAllBooks() {
        return repository.getAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return repository.getBook(id);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Book addBook(@RequestBody NewBook newBook) {
        return repository.add(newBook);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody UpdateBook updateBook) {
        return repository.update(id, updateBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeBook(@PathVariable int id) {
        repository.delete(id);
    }

}
