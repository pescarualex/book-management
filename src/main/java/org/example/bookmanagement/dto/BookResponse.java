package org.example.bookmanagement.dto;

import org.example.bookmanagement.entity.Author;
import org.example.bookmanagement.entity.Category;

import java.util.Set;

public record BookResponse(
        long id,
        String title,
        String isbn,
        Set<CategoryResponse> categories,
        Set<AuthorResponse> authors
) {
}
