package booksmanagement.nextGenTest.repositories;

import booksmanagement.nextGenTest.entities.writings;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BooksRepository extends JpaRepository<writings,Long> {

    writings findByAuthor(String author);
    writings findByTitle(String title);
    writings findByGender(String gender);
}
