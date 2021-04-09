package io.tanzu.labs.tddspringbootbooks;

import java.util.Objects;

public class NewBook {

    private String name;

    public NewBook() {
    }

    public NewBook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewBook newBook = (NewBook) o;
        return Objects.equals(name, newBook.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
