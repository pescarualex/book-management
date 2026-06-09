package org.example.bookmanagement.dto;

import java.util.Set;

public record BookResponse(
        long id,
        String title,
        String isbn,
        Set<CategoryResponse> categories,
        Set<AuthorResponse> authors
) {
}
