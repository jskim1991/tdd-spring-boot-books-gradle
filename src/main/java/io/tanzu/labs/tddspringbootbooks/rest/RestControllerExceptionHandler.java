package io.tanzu.labs.tddspringbootbooks.rest;

import io.tanzu.labs.tddspringbootbooks.repository.exception.NoSuchBookException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class RestControllerExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
//        return ResponseEntity.status(404).body(e.getMessage());
//    }

    @ExceptionHandler(NoSuchBookException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResourceError handleRuntimeException(NoSuchBookException e) {
        return new ResourceError(e.getMessage());
    }

    private class ResourceError {
        private String message;

        public ResourceError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
