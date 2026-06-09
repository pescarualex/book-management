package org.example.bookmanagement.service;

import org.example.bookmanagement.dto.AuthorRequest;
import org.example.bookmanagement.dto.AuthorResponse;
import org.example.bookmanagement.entity.Author;
import org.example.bookmanagement.exception.AuthorNotFoundException;
import org.example.bookmanagement.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void createAuthor(AuthorRequest authorRequest) {
        Author author = new Author();
        mapAuthorToAuthorRequest(authorRequest, author);
        authorRepository.save(author);
    }

    public AuthorResponse getAuthor(long id) {
        Author author = authorRepository.findById(id).orElseThrow(
                () -> new AuthorNotFoundException(id)
        );

        return new AuthorResponse(
                author.getId(),
                author.getFirstName(),
                author.getLastName()
        );
    }

    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(a -> new AuthorResponse(a.getId(), a.getFirstName(), a.getLastName()))
                .toList();
    }

    public void updateAuthor(long id, AuthorRequest authorRequest) {
        Author authorById = authorRepository.findById(id).orElseThrow(
                () -> new AuthorNotFoundException(id)
        );
        mapAuthorToAuthorRequest(authorRequest, authorById);
        authorRepository.save(authorById);
    }

    public void deleteAuthor(long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }

        authorRepository.deleteById(id);
    }


    private void mapAuthorToAuthorRequest(AuthorRequest authorRequest, Author author) {
        author.setFirstName(authorRequest.firstName());
        author.setLastName(authorRequest.lastName());
    }

}
