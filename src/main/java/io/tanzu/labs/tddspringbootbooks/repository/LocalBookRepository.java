package io.tanzu.labs.tddspringbootbooks.repository;

import io.tanzu.labs.tddspringbootbooks.domain.Book;
import io.tanzu.labs.tddspringbootbooks.domain.NewBook;
import io.tanzu.labs.tddspringbootbooks.domain.UpdateBook;
import io.tanzu.labs.tddspringbootbooks.repository.exception.NoSuchBookException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LocalBookRepository implements BookRepository {

    private JpaRepository<BookEntity, Integer> jpaRepository;

    public LocalBookRepository(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Book> getAll() {
        return jpaRepository.findAll()
                .stream()
                .map(BookEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Book getBook(int id) {
        BookEntity bookEntity = jpaRepository.findById(id)
                .orElseThrow(() -> new NoSuchBookException("No such book for id " + id));
        return bookEntity.toDomain();
    }

    @Override
    public Book add(NewBook newBook) {
        BookEntity book = jpaRepository.save(new BookEntity(newBook.getName()));
        return book.toDomain();
    }

    @Override
    public Book update(int id, UpdateBook updateBook) {
        Book book = getBook(id);
        BookEntity updatedBook = jpaRepository.save(new BookEntity(book.getId(), updateBook.getName()));
        return updatedBook.toDomain();
    }

    @Override
    public void delete(int id) {
        Book book = getBook(id);
        jpaRepository.deleteById(book.getId());
    }
}
