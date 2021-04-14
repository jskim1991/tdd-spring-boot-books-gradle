package io.tanzu.labs.tddspringbootbooks.repository;

import io.tanzu.labs.tddspringbootbooks.domain.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BookEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public BookEntity() {
    }

    public BookEntity(String name) {
        this.name = name;
    }

    public BookEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book toDomain() {
        return new Book(this.id, this.name);
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
