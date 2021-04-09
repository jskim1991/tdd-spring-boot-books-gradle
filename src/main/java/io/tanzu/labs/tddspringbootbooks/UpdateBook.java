package io.tanzu.labs.tddspringbootbooks;

import java.util.Objects;

public class UpdateBook {

    private String name;

    public UpdateBook() {
    }

    public UpdateBook(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateBook that = (UpdateBook) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "UpdateBook{" +
                "newName='" + name + '\'' +
                '}';
    }
}
