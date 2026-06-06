package org.example.bookmanagement.service;

import org.example.bookmanagement.dto.AuthorResponse;
import org.example.bookmanagement.dto.BookRequest;
import org.example.bookmanagement.dto.BookResponse;
import org.example.bookmanagement.dto.CategoryResponse;
import org.example.bookmanagement.entity.Author;
import org.example.bookmanagement.entity.Book;
import org.example.bookmanagement.entity.Category;
import org.example.bookmanagement.exception.BookNotFoundException;
import org.example.bookmanagement.repository.AuthorRepository;
import org.example.bookmanagement.repository.BookRepository;
import org.example.bookmanagement.repository.CategoryRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createBook(BookRequest bookRequest) {
        Book book = new Book();

        mapToBookRequest(bookRequest, book);
    }

    public BookResponse getBookById(long id) {
        Book book = bookRepository.findBookByIdWithCategoriesAndAuthors(id);

        if (book == null) {
            throw new BookNotFoundException(id);
        }

        return getBookResponse(book);
    }

    public List<BookResponse> getAllBooks() {
        List<Book> allBooks = bookRepository.findAllBooksWithCategoriesAndAuthors();
        List<BookResponse> allBooksResponse = new ArrayList<>();

        if (!allBooks.isEmpty()) {
            for (Book book : allBooks) {
                BookResponse bookResponse = getBookResponse(book);
                allBooksResponse.add(bookResponse);
            }
        }

        return allBooksResponse;
    }

    public BookResponse updateBook(long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(id)
        );

        mapToBookRequest(bookRequest, book);

        return getBookResponse(book);
    }

    public void deleteBook(long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }


    private void mapToBookRequest(BookRequest bookRequest, Book book) {
        book.setTitle(bookRequest.title());
        book.setIsbn(bookRequest.isbn());

        Set<Author> allAuthorsById = new HashSet<>(authorRepository.findAllById(bookRequest.authorId()));
        Set<Category> allCategoriesById = new HashSet<>(categoryRepository.findAllById(bookRequest.categoryId()));

        book.setCategories(allCategoriesById);
        book.setAuthors(allAuthorsById);

        bookRepository.save(book);
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getCategoryName()
        );
    }

    private AuthorResponse mapToAuthorResponse(Author author) {
        return new AuthorResponse(
                author.getId(),
                author.getFirstName(),
                author.getLastName()
        );
    }

    @NonNull
    private BookResponse getBookResponse(Book book) {
        Set<CategoryResponse> categoryResponses = book.getCategories()
                .stream()
                .map(this::mapToCategoryResponse)
                .collect(Collectors.toSet());

        Set<AuthorResponse> authorResponses = book.getAuthors()
                .stream()
                .map(this::mapToAuthorResponse)
                .collect(Collectors.toSet());

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                categoryResponses,
                authorResponses
        );
    }


}
