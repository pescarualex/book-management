package org.example.bookmanagement.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public record Error(
            LocalDateTime localDateTime,
            int statusCode,
            String error,
            String message,
            String path
    ) {
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> handleConstrainsViolation(ConstraintViolationException e) {
        Error error = new Error(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                "Attributes cannot be null",
                null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Error> handleNoSuchElement(NoSuchElementException e) {
        Error error = new Error(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "No such element found",
                "The object with that id does not exist",
                null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
