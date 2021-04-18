package io.tanzu.labs.tddspringbootbooks.repository.exception;

public class NoSuchBookException extends RuntimeException {

    public NoSuchBookException(String message) {
        super(message);
    }
}
