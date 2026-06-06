package org.example.bookmanagement.controller;

import jakarta.validation.Valid;
import org.example.bookmanagement.dto.BookRequest;
import org.example.bookmanagement.dto.BookResponse;
import org.example.bookmanagement.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Void> createBook(@Valid @RequestBody BookRequest bookRequest) {
        bookService.createBook(bookRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable long id) {
        BookResponse bookById = bookService.getBookById(id);
        return ResponseEntity.ok(bookById);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable long id, @Valid @RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = bookService.updateBook(id, bookRequest);
        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }


}

