package org.example.bookmanagement.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryRequest (
        @NotNull
        String categoryName
) {
}
