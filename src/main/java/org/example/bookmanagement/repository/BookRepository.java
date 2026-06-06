package org.example.bookmanagement.repository;

import org.example.bookmanagement.entity.Book;
import org.example.bookmanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(
            """
            SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.categories LEFT JOIN FETCH b.authors
            """
    )
    List<Book> findAllBooksWithCategoriesAndAuthors();



    @Query(
            """
            SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.categories LEFT JOIN FETCH b.authors WHERE b.id=:id
            """
    )
    Book findBookByIdWithCategoriesAndAuthors(long id);

}
