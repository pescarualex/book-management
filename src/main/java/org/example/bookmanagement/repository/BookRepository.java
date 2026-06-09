package org.example.bookmanagement.repository;

import org.example.bookmanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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
