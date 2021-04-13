package io.tanzu.labs.tddspringbootbooks;

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

    public Book toDomain() {
        return new Book(this.id, this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
