package org.example.bookmanagement.controller;

import org.example.bookmanagement.domain.Author;
import org.example.bookmanagement.dto.AuthorRequest;
import org.example.bookmanagement.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //on admin path, only the librarian will have access.

    @PostMapping("/admin/author")
    public ResponseEntity<Void> createAuthor(@RequestBody AuthorRequest authorRequest) {
        authorService.createAuthor(authorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable long id) {
        authorService.getAuthor(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/author")
    public ResponseEntity<List<Author>> getAllAuthors() {
        authorService.getAllAuthors();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/admin/author/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable long id, @RequestBody AuthorRequest authorRequest) {
        authorService.updateAuthor(id, authorRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("/admin/author/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
