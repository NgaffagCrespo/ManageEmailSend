package booksmanagement.nextGenTest.repositories;

import booksmanagement.nextGenTest.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BooksRepository extends JpaRepository<Books,Long> {

    Books findByBook_author(String author_name);
    Books findByTitle(String book_title);
    Books findByGender(String book_gender);
}
