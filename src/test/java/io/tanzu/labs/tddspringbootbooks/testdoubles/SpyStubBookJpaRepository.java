package io.tanzu.labs.tddspringbootbooks.testdoubles;

import io.tanzu.labs.tddspringbootbooks.repository.BookEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpyStubBookJpaRepository implements JpaRepository<BookEntity, Integer> {

    private List<BookEntity> findAll_returnValue;
    private Optional<BookEntity> findById_returnValue;
    private BookEntity add_return_value;
    private BookEntity update_return_value;

    private int getBook_argument_id;
    private int updateBook_argument_id;
    private int deleteBook_argument_id;

    public SpyStubBookJpaRepository() {
        this.findAll_returnValue = new ArrayList<>();
        this.findById_returnValue = Optional.empty();
    }

    @Override
    public List<BookEntity> findAll() {
        return findAll_returnValue;
    }

    @Override
    public List<BookEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<BookEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<BookEntity> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {
        deleteBook_argument_id = integer;
    }

    @Override
    public void delete(BookEntity entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends BookEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends BookEntity>S save(S entity) {
        if (entity.getId() != null) {
            updateBook_argument_id = entity.getId();
        }

        if (add_return_value != null) {
            return (S) add_return_value;
        }
        else if (update_return_value != null) {
            return (S) update_return_value;
        }
        return entity;
    }

    @Override
    public <S extends BookEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BookEntity> findById(Integer integer) {
        getBook_argument_id = integer;
        return findById_returnValue;
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends BookEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<BookEntity> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BookEntity getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends BookEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BookEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends BookEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends BookEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BookEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BookEntity> boolean exists(Example<S> example) {
        return false;
    }

    public void setFindAll_returnValue(List<BookEntity> findAll_returnValue) {
        this.findAll_returnValue = findAll_returnValue;
    }

    public void setFindById_returnValue(BookEntity findById_returnValue) {
        this.findById_returnValue = Optional.of(findById_returnValue);
    }

    public void setAdd_return_value(BookEntity add_return_value) {
        this.add_return_value = add_return_value;
    }

    public void setUpdate_return_value(BookEntity update_return_value) {
        this.update_return_value = update_return_value;
    }

    public int getGetBook_argument_id() {
        return getBook_argument_id;
    }

    public int getUpdateBook_argument_id() {
        return updateBook_argument_id;
    }

    public int getDeleteBook_argument_id() {
        return deleteBook_argument_id;
    }
}
