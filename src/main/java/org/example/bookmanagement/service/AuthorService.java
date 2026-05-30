package org.example.bookmanagement.service;

import org.example.bookmanagement.domain.Author;
import org.example.bookmanagement.dto.AuthorRequest;
import org.example.bookmanagement.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void createAuthor(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setFirstName(authorRequest.firstName());
        author.setLastName(authorRequest.lastName());

        authorRepository.save(author);
    }

    public Author getAuthor(long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void updateAuthor(long id, AuthorRequest authorRequest) {
        Author authorById = authorRepository.findById(id).orElseThrow();
        authorById.setFirstName(authorRequest.firstName());
        authorById.setLastName(authorRequest.lastName());

        authorRepository.save(authorById);
    }

    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }

}
