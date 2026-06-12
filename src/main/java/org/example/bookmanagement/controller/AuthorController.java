package org.example.bookmanagement.controller;

import jakarta.validation.Valid;
import org.example.bookmanagement.dto.AuthorRequest;
import org.example.bookmanagement.dto.AuthorResponse;
import org.example.bookmanagement.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@Validated
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @PostMapping()
    public ResponseEntity<Void> createAuthor(@Valid @RequestBody AuthorRequest authorRequest) {
        authorService.createAuthor(authorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable long id) {
        AuthorResponse author = authorService.getAuthor(id);
        return ResponseEntity.ok(author);
    }

    @GetMapping()
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        List<AuthorResponse> allAuthors = authorService.getAllAuthors();
        return ResponseEntity.ok(allAuthors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable long id, @Valid @RequestBody AuthorRequest authorRequest) {
        authorService.updateAuthor(id, authorRequest);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }


}
