package org.example.bookmanagement.dto;

public record AuthorResponse(
        long id,
        String firstName,
        String lastName
) {
}
