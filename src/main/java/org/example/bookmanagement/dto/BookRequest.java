package org.example.bookmanagement.dto;

import java.util.Set;

public record BookRequest(
        String title,
        String isbn,
        Set<Long> categoryId,
        Set<Long> authorId
) {
}
