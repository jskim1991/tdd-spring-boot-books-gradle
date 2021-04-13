package io.tanzu.labs.tddspringbootbooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LocalBookRepository implements BookRepository {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Override
    public List<Book> getAll() {
        return bookJpaRepository.findAll()
                .stream()
                .map(BookEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Book getBook(int id) {
        BookEntity bookEntity = bookJpaRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new RuntimeException("No such book for id " + id));
        return bookEntity.toDomain();
    }

    @Override
    public Book add(NewBook newBook) {
        BookEntity savedEntity = bookJpaRepository.save(new BookEntity(newBook.getName()));
        return savedEntity.toDomain();
    }

    @Override
    public Book update(int id, UpdateBook updateBook) {
        BookEntity bookEntity = bookJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such book for id " + id));
        bookEntity.setName(updateBook.getName());
        return bookJpaRepository.save(bookEntity).toDomain();
    }

    @Override
    public void delete(int id) {
        BookEntity bookEntity = bookJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such book for id " + id));
        bookJpaRepository.delete(bookEntity);
    }

}
