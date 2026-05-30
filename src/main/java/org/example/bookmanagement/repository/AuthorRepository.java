package org.example.bookmanagement.repository;

import org.example.bookmanagement.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
