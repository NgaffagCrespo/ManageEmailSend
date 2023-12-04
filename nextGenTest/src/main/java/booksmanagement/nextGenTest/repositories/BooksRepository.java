package booksmanagement.nextGenTest.repositories;

import booksmanagement.nextGenTest.entities.Writings;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BooksRepository extends JpaRepository<Writings,Long> {

    Writings findByAuthor(String author);
    Writings findByTitle(String title);
    Writings findByGender(String gender);
}
