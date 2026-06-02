package org.example.bookmanagement.dto;

public record AuthorResponse (
    long id,
    String firstname,
    String lastname
) {}
