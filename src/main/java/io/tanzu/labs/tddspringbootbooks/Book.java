package io.tanzu.labs.tddspringbootbooks;

public class Book {

    private int id;
    private String name;

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(int id, NewBook newBook) {
        this.id = id;
        this.name = newBook.getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
