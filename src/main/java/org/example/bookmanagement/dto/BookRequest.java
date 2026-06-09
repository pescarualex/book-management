package org.example.bookmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record BookRequest(
        @NotBlank String title,
        @NotBlank String isbn,
        @NotEmpty Set<Long> categoryIds,
        @NotEmpty Set<Long> authorIds
) {
}
