package org.example.bookmanagement.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank
        String categoryName
) {
}
