package org.example.bookmanagement.dto;

import jakarta.validation.constraints.NotNull;

public record AuthorRequest(
        @NotNull
        String firstName,

        @NotNull
        String lastName
) {
}
